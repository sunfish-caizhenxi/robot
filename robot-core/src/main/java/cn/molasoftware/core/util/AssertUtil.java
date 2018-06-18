package cn.molasoftware.core.util;

import java.util.Collection;

/**
 * 参数验证
 * 
 * @author caizx
 * 
 */
public class AssertUtil {

	/**
	 * 判断一个字符串是否超过最大长度</br>
	 * 注意：如果字符串为空，抛java.lang.IllegalArgumentException异常</br> 参数不允许为空</br>
	 * 
	 * @param str
	 *            字符串
	 * @param maxLength
	 *            最大长度
	 * @param message
	 *            抛出的异常信息
	 */
	public static void assertMaxLength(String str, int maxLength, String message) {
		if (StrUtil.isBlank(str)) {
			throw new IllegalArgumentException("参数不允许为空.");
		}
		if (str.length() > maxLength) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断一个字段是否合法的字段名称</br> 合法的字段名称必需以a-z或者A-Z开头
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param message
	 *            抛出的异常信息
	 */
	public static void assertFieldName(String fieldName, String message) {
		if (StrUtil.isBlank(fieldName)) {
			throw new IllegalArgumentException(message);
		}
		boolean isFieldName = fieldName.matches("^[a-zA-Z]+$");
		if (!isFieldName) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断一个对象是否为空</br>
	 * 
	 * @param obj
	 *            对象
	 * @param message
	 *            如果为空,抛出的异常信息
	 */
	public static void assertNotNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断一个字符串是否为空</br>
	 * 
	 * @param str
	 *            字符串
	 * @param message
	 *            如果为空,抛出的异常信息
	 */
	public static void assertNotBlank(String str, String message) {
		if (StrUtil.isBlank(str)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 列表不为空则正常，为空则抛异常</br>
	 * 
	 * @param list
	 *            列表
	 * @param message
	 *            如果列表为空，抛出的异常信息
	 */
	public static void assertNotBlank(Collection<?> collection, String message) {
		if (collection!=null && !collection.isEmpty()) {
			return;
		}
		throw new IllegalArgumentException(message);
	}


	/**
	 * 判断一个布尔值是否为真,不为真抛异常 </br>
	 * 
	 * @param flag
	 *            列表
	 * @param message
	 *            如果flag为空，抛出的异常信息
	 */

	public static void assertTrue(boolean flag, String message) {
		if (!flag) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断一个整数，是否大于0</br>
	 * 
	 * @param num
	 *            整数
	 * @param message
	 *            为空，或者小于0，抛出的异常信息
	 */
	public static void assertGreatZero(Integer num, String message) {
		if (num == null || num <= 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断一个Long，是否大于0</br>
	 * 
	 * @param num
	 *            Long
	 * @param message
	 *            为空，或者小于0，抛出的异常信息
	 */

	public static void assertGreatZero(Long num, String message) {
		if (num == null || num <= 0) {
			throw new IllegalArgumentException(message);
		}
	}


	/**
	 * 判断二个整数是否相等 </br>
	 * 
	 * @param num1
	 *            整数1
	 * @param num2
	 *            整数2
	 * @param message
	 *            不相等时，抛出的异常信息
	 */
	public static void assertEquals(int num1, int num2, String message) {
		if (num1 == num2) {
			return;
		}
		throw new IllegalArgumentException(message);
	}

	/**
	 * 判断二个字符串是否相等，忽略大小写</br>
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * @param message
	 *            不相等时，抛出的异常信息
	 */
	public static void assertEqualsIgnoreCase(String str1, String str2, String message) {
		if (str1.equalsIgnoreCase(str2)) {
			return;
		}
		throw new IllegalArgumentException(message);
	}

}
