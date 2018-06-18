package cn.molasoftware.core.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

public class StrUtil {
	public static String transBlank(String original) {
		return StrUtil.isBlank(original) ? "" : original;
	}
	/**
	 * 对字符串转码，用于解决中文乱码问题。
	 * 
	 * @param original原始字符串。
	 * @param charset字符集编码："UTF-8","ISO-8859-1","GBK","GB2312"等.
	 * @return 转码后的字符串.
	 */
	public static String transCharset(String original, String charset) {
		if (original == null)
			return null;
		StringBuffer sb = new StringBuffer(original.length() + 16);
		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = String.valueOf(c).getBytes(charset);
				} catch (Exception ex) {
					ex.printStackTrace();
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 把中文编码字符串转码为ISO-8859-1编码字符串。 对于附件下载文件名出现乱码的情况下有用
	 * 
	 * @param chsStr
	 * @return
	 */
	public static String transChs2Iso(String chsStr) {
		if (chsStr == null) {
			return null;
		}
		try {
			return new String(chsStr.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return chsStr;
		}
	}

	/**
	 * 把字符串pOriginalString转化为中文字符串（用来解决乱码问题）
	 * 
	 * @param pOriginalString 需要解码的字符串 说明：如果pOriginalString不包含乱码，则直接返回，否则进行解码处理.
	 * @return 不存在乱码的字符串
	 */
	public static String toChinese(final String pOriginalString) {
		if (pOriginalString == null) {
			return null;
		}

		if (isChinese(pOriginalString)) {
			return pOriginalString;
		}

		try {
			return new String(pOriginalString.getBytes("ISO-8859-1"), "gb2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 判断一个字符串中是否有汉字 判断依据： 如果字符串pValue中存在值大于256的字符就认为有汉字，否
	 * 则就没有（非汉字数据的整数值不可能大于256的，因为他只有8位）
	 * 
	 * @param pValue要检查的字符串.
	 * @return boolean
	 */
	public static boolean isChinese(String pValue) {
		if(isBlank(pValue)){
			return false;
		}
		for (int i = 0; i < pValue.length(); i++) {
			if ((int) pValue.charAt(i) > 256)
				return true;
		}
		return false;
	}

	/**
	 * 根据指定的分割符分割字符串.
	 * 
	 * @param pStr一特定的字符串，以某分隔符分隔.
	 * @param delim指定的分隔符.
	 * @return List.
	 */
	
	public static List<String> splitStr2List(String pStr, String delim) {
		List<String> list = new ArrayList<String>();
		if (pStr == null || delim == null)
			return list;
		StringTokenizer st = new StringTokenizer(pStr, delim);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

	/**
	 * 根据指定的分割符分割字符串.
	 * 
	 * @param str
	 *            一特定的字符串，以某分隔符分隔.
	 * @param delim
	 *            指定的分隔符.
	 * @return 数组。str 为 null 时返回长度为0的数组。
	 */
	
	public static String[] splitStr2Array(String str, String delim) {
		List<String> list = splitStr2List(str, delim);
		if (list.isEmpty()) {
			return new String[0];
		} else {
			String[] strs = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				strs[i] = (String) list.get(i);
			}
			return strs;
		}
	}

	/**
	 * 把字符串数组转换成用分割符分割的字符串.
	 * 
	 * @param pStrArray字符串数组.
	 * @param delim指定的分隔符.
	 * @return String
	 */
	public static String constructStr(Object[] pStrArray, String delim) {
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
	
	/**
	 * 把字符串数组转换成用分割符分割的字符串.
	 * 
	 * @param pStrArray字符串数组.
	 * @param delim指定的分隔符.
	 * @return String
	 */
	public static String constructStr(Collection<?> pStrList, String delim) {
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
	
	/**
	 * 去除串前后空格，若为null，转换成长度为0的串.
	 * 
	 * @param pStr原字符串.
	 * @return 返回前后空格的字符串.
	 */
	public static String trim(String pStr) {
		return pStr == null ? "" : pStr.trim();
	}

	/**
	 * 去掉所有空格
	 * @param s
	 * @return
	 */
	public static String trimAllSpace(String s) {
		if (s == null){
			return null;
		}
		return s.replaceAll(" ", "");
	}


	/**
	 * 判断字符串是否为空. 如果字符串为null或者全为空格或者为“null”，都返回true.
	 * 
	 * @param pStr要检查的字符串.
	 * @return boolean 值.
	 */
	public static boolean isBlank(String pStr) {
		return pStr == null || pStr.trim().length() == 0 || pStr.equalsIgnoreCase("null");
	}

	/**
	 * 判断字符串是否不为空。 字符串为null,或全是空格，或者是"null"，都返回false.
	 * 
	 * @param pStr
	 * @return true/false. 不为空(null, " ", "null")时返回true.
	 * @see isBlankStr(String).
	 */
	public static boolean isNotBlank(String pStr) {
		return !isBlank(pStr);
	}

	/**
	 * 省略字符串，只取字符串前面length个字符加上省略号返回.
	 * 
	 * @param pStr字符串。
	 * @param length要保留的长度（英文字符长度，一个汉字占两个字符长度）。
	 * @return 如果pStr的长度小于length，则返回原串。
	 */
	public static String omit(String pStr, int length) {
		if (pStr == null || pStr.length() == 0 || length <= 0)
			return pStr;
		int len = 0;
		int count = 0; // 最终要返回的中英文字符个数.
		for (int i = 0; i < pStr.length(); i++) {
			char c = pStr.charAt(i);
			// 转换成英文字符长度.
			if (c > 256) {
				len += 2;
			} else {
				len += 1;
			}
			// 小于要返回的英文字符个数。
			if (len <= length) {
				count++;
			} else {
				break;
			}
		}
		if (count >= pStr.length())
			return pStr;
		// 要去掉省略号的长度(1个字符).
		String str = pStr.substring(0, count - 1);
		return str + "...";
	}

	/**
	 * 用指定的字符替换回车符和字符'\r\n'
	 * 
	 * @param str含有回车符的字符串。
	 * @param value用来代替回车的字符
	 * @return 替换后的字符串.
	 */
	public static String replaceEnter(String str, String value) {
		if (str == null || str.length() == 0)
			return str;
		return str.replaceAll("\r\n", value);
	}

	/**
	 * 通过Collection构造sql in字符串
	 * @param c
	 * @return
	 */
	
	public static String getInCondition(Collection<String> c) {
		StringBuffer sb = new StringBuffer(256);
		int flag = 0;
		if (c == null || c.isEmpty()) {
			return null;
		}
		for (Iterator<String> it = c.iterator(); it.hasNext();) {
			flag++;
			if (flag == 1) {
				sb.append("'" + it.next() + "'");
			} else {
				sb.append(",'" + it.next() + "'");
			}
		}
		return sb.toString();
	}

	/**
	 * 为了能正确排序含有数字的字符串。需要将中间的数字前补０。
	 * 
	 * @param str形如 ABC1 ->　ABC001
	 * @param len格式化的长度。
	 * @return String.
	 */
	public static String formatForOrder(String str, int len) {
		if (isBlank(str))
			return null;
		int index = -1;
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				index = i;
			} else {
				break;
			}
		}
		if (index >= 0) {
			StringBuffer sb = new StringBuffer(16);
			sb.append(str.substring(0, index));
			String sNum = str.substring(index);
			for (int i = 0; i < len - sNum.length(); i++) {
				sb.append("0");
			}
			sb.append(sNum);
			return sb.toString();
		} else {
			return str;
		}
	}

	/**
	 * 增加单引号(单字符)
	 * @param pValue
	 * @return
	 */
	public static String addSQM(String pValue) {
		if (!isBlank(pValue)) {
			return "'" + pValue + "'";
		}
		return "''";
	}

	/**
	 * 增加单引号(字符数组)
	 * @param pValues
	 * @return
	 */
	public static String addSQM(String[] pValues) {
		if (pValues!=null&&pValues.length>0) {
			String result = "";
			for (int i = 0; i < pValues.length; i++) {
				if (i > 0)
					result += ",";
				result += addSQM(pValues[i]);
			}
			return result;
		}
		return "''";
	}

	/**
	 * 判断字符是否由startValue开头,endValue结束
	 */
	public static boolean isBetweenWith(String pValue, String startValue, String endValue) {
		if (pValue == null) {
			return false;
		}
		if (pValue.startsWith(startValue) && pValue.endsWith(endValue)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否操作符
	 */
	public static boolean isOperator(String pValue) {
		if (pValue != null && pValue.length() == 1 && "+-*/".indexOf(pValue) > -1) {
			return true;
		}
		return false;
	}

	/**
	 * 将字符串反转输出
	 * 
	 * @param pString
	 * @return
	 */
	public static String reverse(String pString) {
		StringBuffer temp = new StringBuffer(pString);
		return temp.reverse().toString();
	}
	
	/**
	 * 用指定的新字符穿代替指定的旧字符串
	 * 采用字符的形式替换所有字符
	 * @param orgStr
	 *            要进行替换的字符串
	 * @param oldValue
	 *            旧字符串
	 * @param newValue
	 *            新字符串
	 * @return
	 */
	public static String replace(String orgStr, String oldValue, String newValue) {
		if (orgStr == null || orgStr.length() == 0)
			return orgStr;
		return orgStr.replace(oldValue, newValue);
	}
	
	/**
	 * 判断字符出现的次数
	 * @param pValue
	 * @param appear
	 * @return
	 */
	public static int getAppearCount(String pValue, String appearStr){
		if(pValue==null||appearStr==null){
			return 0;
		}else{
			if(pValue.endsWith(appearStr)){
				return pValue.split(appearStr).length;
			}else{
				return pValue.split(appearStr).length-1;
			}
		}
	}
	
	/**
	 * 对字符串进行转义
	 * 
	 * @param content
	 *            文本
	 * @return 转义后的字符串
	 */
	public static String escapePattern(final String content) {// NOPMD
		if (content == null) {
			return null;
		}

		int stringLength = content.length();
		// StringBuilder buf = new StringBuilder();
		StringBuilder buf = new StringBuilder((int) (stringLength * 1.1));
		for (int i = 0; i < stringLength; ++i) {
			char c = content.charAt(i);
			if (c == '\\' || c == '?' || c == '+' || c == '*' || c == '[' || c == ']' || c == '{' || c == '}' || c == '(' || c == ')' || c == '-' || c == '$' || c == '|') {
				buf.append('\\');
			}
			buf.append(c);
		}
		return buf.toString();
	}

	/**
	 * 对html标签进行转义
	 * 
	 * @param str
	 *            字符串
	 * @return 转义后的字符串
	 */
	public static String escapeHTMLTags(final String str) {
		if (str == null) {
			return null;
		}
		// 替换时先判断是否存在需要替换的字符,提高性能
		if (str.indexOf('<') == -1 && str.indexOf('>') == -1 && str.indexOf('"') == -1) {
			return str;
		}

		int stringLength = str.length();
		// StringBuilder buf = new StringBuilder();
		StringBuilder buf = new StringBuilder((int) (stringLength * 1.1));
		for (int i = 0; i < stringLength; ++i) {
			char c = str.charAt(i);

			switch (c) {
			case '<':
				buf.append("&lt;");
				break;
			case '>':
				buf.append("&gt;");
				break;
			case '"':
				buf.append("&quot;");
				break;
			default:
				buf.append(c);
			}
		}
		return buf.toString();
	}

	/**
	 * 转换javascript参数</br> 将'换成\'</br> 将"转换成&quot;
	 * 
	 * @param str
	 *            需要转换的内容
	 * @return 转换后的内容
	 */
	public static String escapeJavascriptParam(final String str) {
		if (str == null) {
			return null;
		}
		// 替换时先判断是否存在需要替换的字符,提高性能
		if (str.indexOf('"') == -1 && str.indexOf('\'') == -1) {
			return str;
		}

		int stringLength = str.length();
		// StringBuilder buf = new StringBuilder();
		StringBuilder buf = new StringBuilder((int) (stringLength * 1.1));
		for (int i = 0; i < stringLength; ++i) {
			char c = str.charAt(i);

			switch (c) {
			case '\'':
				buf.append("\\'");
				break;
			case '"':
				buf.append("&quot;");
				break;
			default:
				buf.append(c);
			}
		}
		return buf.toString();
	}

	
	/**
	 * 返回字符串的hashcode
	 * 
	 * @param str
	 *            字符串
	 * @return 哈希值
	 */
	public static long getHashCode(String str) {
		int h = 0;
		char val[] = str.toCharArray();
		for (int i = 0; i < val.length; i++) {
			h = 31 * h + val[i];
		}
		return Math.abs((long) h);
	}
	
	/**
	 * 统计字符出现次数.
	 * 
	 * @param content
	 * @param split
	 * @return
	 */
	public static int countString(String content, String split) {
		if (StringUtils.isEmpty(content)) {
			return 0;
		}
		int count = 0;
		int index = -1;
		while (true) {
			index = content.indexOf(split, index + 1);
			if (index == -1) {
				break;
			}
			count++;
		}
		return count;
	}
	
	/**
	 * 首字母变小写.
	 * 
	 * @param word
	 * @return
	 */
	public static String firstCharToLowerCase(String word) {
		if (word.length() == 1) {
			return word.toLowerCase();
		}
		char c = word.charAt(0);
		char newChar;
		if (c >= 'A' && c <= 'Z') {
			newChar = (char) (c + 32);
		} else {
			newChar = c;
		}
		return newChar + word.substring(1);
	}

	/**
	 * 首字母变大写.
	 * 
	 * @param word
	 * @return
	 */
	public static String firstCharToUpperCase(String word) {
		if (word.length() == 1) {
			return word.toUpperCase();
		}
		char c = word.charAt(0);
		char newChar;
		if (c >= 'a' && c <= 'z') {
			newChar = (char) (c - 32);
		} else {
			newChar = c;
		}
		return newChar + word.substring(1);
	}
	
	/**
	 * 判断是否为英文字母.
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		return false;
	}
	
	/**
	 * 用于MySQL全文检索中文编码.
	 * <p>
	 * 
	 * @param str
	 * @return
	 */
	public static String getIntString(final String str) {
		if (str == null) {
			return "";
		}
		byte[] bytes;
		try {
			bytes = str.toLowerCase().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		StringBuilder sb = new StringBuilder();
		int iscn = 0;
		for (int i = 0; i < bytes.length; i++) {
			int j = bytes[i];
			if (bytes[i] < 0) {
				j = j * (-1);
				if (j < 10) {
					sb.append('0');
				}
				sb.append(j);
				iscn++;
				if (iscn == 2) {
					sb.append(' ');
					iscn = 0;
				}
			} else {
				byte[] b = new byte[] { bytes[i] };
				for (int n = 0; n < b.length; n++) {
					String str1 = "000" + b[n];
					str1 = str1.substring(str1.length() - 4);
					sb.append(str1).append(' ');
				}
			}
		}
		return sb.toString();
	}
}
