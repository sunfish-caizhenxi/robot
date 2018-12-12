package cn.molasoftware.core.util;

import java.util.List;
import java.util.Set;

public class SQLUtil {

	/**
	 * 对SQL语句进行转义
	 * 
	 * @param param
	 *            SQL语句
	 * @return 转义后的字符串
	 */
	public static String escapeSQLParam(final String param) {
		int stringLength = param.length();
		StringBuilder buf = new StringBuilder((int) (stringLength * 1.1));
		for (int i = 0; i < stringLength; ++i) {
			char c = param.charAt(i);
			switch (c) {
			case 0: /* Must be escaped for 'mysql' */
				buf.append('\\');
				buf.append('0');
				break;
			case '\n': /* Must be escaped for logs */
				buf.append('\\');
				buf.append('n');
				break;
			case '\r':
				buf.append('\\');
				buf.append('r');
				break;
			case '\\':
				buf.append('\\');
				buf.append('\\');
				break;
			case '\'':
				buf.append('\\');
				buf.append('\'');
				break;
			case '"': /* Better safe than sorry */
				buf.append('\\');
				buf.append('"');
				break;
			case '\032': /* This gives problems on Win32 */
				buf.append('\\');
				buf.append('Z');
				break;
			default:
				buf.append(c);
			}
		}
		return buf.toString();
	}
	
	/**
	 * 创建in语句
	 * @param list
	 * @return
	 */
	public static String toIn(List<String> list) {
		if(list==null || list.isEmpty()){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			str = escapeSQLParam(str);
			sb.append("'" + str + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 创建in语句
	 * @param set
	 * @return
	 */
	public static String toIn(Set<String> set) {
		if(set==null || set.isEmpty()){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String str : set) {
			str = escapeSQLParam(str);
			sb.append("'" + str + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	/**
	 * 创建select语句
	 * @param array
	 * @return
	 */
	public static String toSelect(String[] array) {
		if(array==null || array.length==0){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String str : array) {
			str = escapeSQLParam(str);
			sb.append(str + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	
	/**
	 * 创建select语句
	 * @param array
	 * @param func 函数名称
	 * @return
	 */
	public static String toSelect(String[] array, String func) {
		if(array==null || array.length==0){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String str : array) {
			str = escapeSQLParam(str);
			str = func+"("+str+") "+str;
			sb.append(str + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
