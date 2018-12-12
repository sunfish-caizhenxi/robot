package cn.molasoftware.core.util;


/**
 * 字符串16进制转换
 * 
 * @author caizx
 * 
 */
public class Base16Util {

	/**
	 * 将字符串转换成 16进制字符串</br>
	 * 
	 * @param s 字符串
	 * @return 转换后的16进制字符串
	 */
	public static String encode(String str) {
		try {
			StringBuilder sb = new StringBuilder();
			char[] arr = str.toCharArray();
			for (char c : arr) {
				String s4 = Integer.toHexString(c);
				sb.append(s4);
			}
			return sb.toString();
		}
		finally {
		}
	}

	/**
	 * 将 16进制字符串转换成字符串,不支持中文。</br>
	 * 
	 * @param s 16进制字符串
	 * @return 转换后的字符串
	 */
	public static String decode(String s) {
		try {
			byte[] baKeyword = new byte[s.length() / 2];
			for (int i = 0; i < baKeyword.length; i++) {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			}
			return new String(baKeyword);// UTF-16le:Not
		}
		finally {
		}
	}
}
