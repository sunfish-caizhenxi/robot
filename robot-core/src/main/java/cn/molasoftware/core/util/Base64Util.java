package cn.molasoftware.core.util;

/**
 * 字符串64进制转换
 * @author caizx
 *
 */
public class Base64Util {
	/**
	 * 对字符串进行base64解码</br>
	 * 
	 * @param content
	 *            字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String content) {
		try {
			if (StrUtil.isBlank(content)) {
				return content;
			} else {
				byte[] decodeQueryString = org.apache.commons.codec.binary.Base64.decodeBase64(content.getBytes());
				return new String(decodeQueryString);
			}
		} finally {
		}
	}

	/**
	 * 对字符串进行Base64编码</br>
	 * 
	 * @param content
	 *            字符串
	 * @return 编码后的字符串
	 */
	public static String encode(String content) {
		try {
			if (StrUtil.isBlank(content)) {
				return content;
			}
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(content.getBytes()));
		} finally {

		}
	}

	public static void main(String[] args) {
		String str = "你好";
		String encode = Base64Util.encode(str);
		System.out.println(encode);
		String decode = Base64Util.decode(encode);
		System.out.println(decode);
	}
}
