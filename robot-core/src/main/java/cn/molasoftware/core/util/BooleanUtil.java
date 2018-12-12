/**
 * 
 */
package cn.molasoftware.core.util;

/**
 * 布尔工具类
 * @author caizx
 *
 */
public class BooleanUtil {
	/**
	 * 解析字符串为boolean值 yes, y, true, on, 1 都解析为true(不区分大小写)，否则解析为false
	 * 
	 * @param str侍解析字符串
	 * @return 解析结果
	 */
	public static boolean parseBoolean(String str) {
		return ((str != null) && (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("y") || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("on") || str.equalsIgnoreCase("1")));
	}
	
	/**
	 * 返回布尔值
	 * @param b 为null，返回false
	 * @return
	 */
	public static boolean parseBoolean(Boolean b) {
		if(b==null){
			return false;
		}
		return b.booleanValue();
	}
}
