/**
 * 
 */
package cn.molasoftware.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caizx
 *
 */
public class ParameterUtil {

	//以美元符号开始的参数表示法，如${id}
	public final static int PARAMETER_TYPE_DOLLAR=1;
	//已经冒号开发的参数表示法,如:id
	public final static int PARAMETER_TYPE_COLON=2;
	
	/**
	 * ${}格式的参数替换
	 * @param strValue 要进行参数替换的字符串
	 * @param valuesMap 值集合
	 * @return
	 */
	public static String translate(String strValue, Map<String,Object> valuesMap){
		return translate( 1, strValue, valuesMap);
	}
	
	/**参数替换
	 * @param type 参数类型,1表示处理格式为${}的参数，如${id}, 2表示处理冒号开头的参数，如:id
	 * @param strValue 要进行参数替换的字符串
	 * @param valuesMap 值集合
	 * @return
	 */
	public static String translate(int type, String strValue, Map<String,Object> valuesMap){
		return translate( type, strValue, valuesMap, "");
	}
	
	/**
	 * ${}格式参数替换，如果参数对应的值在valuesMap找不到，则用defaultValue指定的代替，如果defaultValue为null，这不进行替换
	 * @param strValue 要进行参数替换的字符串
	 * @param valuesMap 值集合
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String translate(String strValue, Map<String,Object> valuesMap, String defaultValue){
		return translate( 1, strValue, valuesMap, "");
	}
	
	/**
	 * 参数替换，如果参数对应的值在valuesMap找不到，则用defaultValue指定的代替，如果defaultValue为null，这不进行替换
	 * @param type 参数类型,1表示处理格式为${}的参数，如${id}, 2表示处理冒号开头的参数，如:id
	 * @param strValue 要进行参数替换的字符串
	 * @param valuesMap 值集合
	 * @param defaultValue 默认值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String translate(int type, String strValue, Map<String,Object> valuesMap, String defaultValue){
		Collection<String> paramNames = getParameterNames(type, strValue);
		if(paramNames==null||paramNames.isEmpty()){
			return strValue;
		}
		String returnStr = strValue;
		for(String paramName : paramNames){
			// 获取值
			String paramValue = null;
			Object value = valuesMap.get(paramName);
			if (value == null) {
				paramValue = defaultValue;
			} else if (value instanceof Date) {
				paramValue = format((Date) value);
			} else if (value instanceof Object[]){
				paramValue = constructStr((Object[])value, ",");
			} else if (value instanceof Collection){
				paramValue = constructStr((Collection)value, ",");
			} else {
				paramValue = value.toString();
			}
			if (paramValue == null) {
				paramValue=defaultValue;
			}
			if (paramValue != null) {
				// 替换字符串
				if(PARAMETER_TYPE_DOLLAR == type){
					returnStr = returnStr.replaceAll("\\$\\{" + paramName + "\\}", paramValue);
				}else if(PARAMETER_TYPE_COLON == type){
					returnStr = returnStr.replaceAll(":" + paramName, paramValue);
				}
			}
		}
		return returnStr;
	}
	
	/**
	 * 获取字符串中的参数
	 * @param strValue 要获取参数的字符串
	 * @return
	 */
	public static List<String> getParameterNames(String strValue){
		return getParameterNames(1, strValue);
	}
	
	/**
	 * 获取字符串中的参数
	 * @param type 参数类型,1表示处理格式为${}的参数，如${id}, 2表示处理冒号开头的参数，如:id
	 * @param strValue 要获取参数的字符串
	 * @return
	 */
	public static List<String> getParameterNames(int type, String strValue){
		if (strValue == null || strValue.length() == 0) {
			return null;
		}
		String regEx = "";
		if(PARAMETER_TYPE_DOLLAR == type){
			//处理格式为${}的参数
			regEx = "\\$\\{([^\\x00-\\xff]|\\w|\\.|\\-|\\:)+\\}";
		}else if(PARAMETER_TYPE_COLON == type){
			//处理冒号开始的参数，如:id
			regEx = ":(\\w|\\-)+";
		}
		// 获取字符串中的参数
		List<String> paramNames = new ArrayList<String>();
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(strValue);
		while (matcher.find()) {
			String paramName = matcher.group();
			if(PARAMETER_TYPE_DOLLAR == type){
				paramName = paramName.substring(2, paramName.length() - 1);
			}else if(PARAMETER_TYPE_COLON == type){
				paramName = paramName.substring(1, paramName.length());
			}
			paramNames.add(paramName);
		}
		return paramNames;
	}
	
	/**
	 * 处理字符串里的参数格式，返回参数名列表，同时用新的参数表示法替换老的参数表示法
	 * 如用?替换${id}，用?替换:id
	 * @param type 参数类型,1表示处理格式为${}的参数，如${id}, 2表示处理冒号开头的参数，如:id
	 * @param strValue 要获取参数的字符串
	 * @param newType 新的参数符号
	 * @return map, key=newStr表示替换后的字符串，key=paramNames表示原字符串里的参数名集合
	 */
	public static Map<String,Object> handleParameterName(int type, String strValue, String newType){
		if (strValue == null || strValue.length() == 0) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		String regEx = "";
		if(PARAMETER_TYPE_DOLLAR == type){
			//处理格式为${}的参数
			regEx = "\\$\\{([^\\x00-\\xff]|\\w|\\.|\\-|\\:)+\\}";
		}else if(PARAMETER_TYPE_COLON == type){
			//处理冒号开始的参数，如:id
			regEx = ":(\\w|\\-)+";
		}
		// 获取字符串中的参数
		List<String> paramNames = new ArrayList<String>();
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(strValue);
		while (matcher.find()) {
			String paramName = matcher.group();
			strValue = strValue.replaceAll(paramName, newType);
			if(PARAMETER_TYPE_DOLLAR == type){
				paramName = paramName.substring(2, paramName.length() - 1);
			}else if(PARAMETER_TYPE_COLON == type){
				paramName = paramName.substring(1, paramName.length());
			}
			paramNames.add(paramName);
		}
		map.put("newStr", strValue);
		map.put("paramNames", paramNames);
		return map;
	}
	
	private static String constructStr(Object[] pStrArray, String delim) {
		if (pStrArray == null || pStrArray.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < pStrArray.length; i++) {
			if (i != 0) {
				sb.append(delim);
			}
			sb.append(pStrArray[i]);
		}
		return sb.toString();
	}
	
	private static String constructStr(Collection<?> pStrList, String delim) {
		if (pStrList == null || pStrList.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer("");
		int i =0;
		for (Object o : pStrList) {
			if (i != 0) {
				sb.append(delim);
			}
			sb.append(o);
			i++;
		}
		return sb.toString();
	}
	
	private static String format(Date date) {
		if (date == null) {
			return null;
		}
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String expression ="[sdfsdf]+[sdfsdf]+[sdfsdf]";
		expression = expression.replaceAll(" ", "");
		expression = expression.replace("[", "${");
		expression = expression.replace("]", "}");
		System.out.println(expression);
		Map<String,Object> valuesMap = new HashMap<String,Object>();
		valuesMap.put("name", "GeorgeXie");
		valuesMap.put("code", 10000);
		System.out.println(ParameterUtil.translate(PARAMETER_TYPE_DOLLAR, "http://127.0.0.1/project.do?name=${name}&code=${code}&code=${code}",valuesMap));
		
		String url = "http://127.0.0.1/project.do?name=:name&code=:code&code=:code";
		System.out.println(ParameterUtil.handleParameterName(PARAMETER_TYPE_COLON, url,"?"));
	}
}
