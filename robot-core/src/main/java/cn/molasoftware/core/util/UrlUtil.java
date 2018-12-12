package cn.molasoftware.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map.Entry;

public final class UrlUtil {

	public static String hostOfUrl(String url) {
		return url.replaceAll(".*://", "").replaceAll("/.*", "").replaceAll(":.*", "");
	}

	public static String appendUriMethod2Url(String url, String uriMethod) {
		String url2 = url + uriMethod;
		// url = StringUtils.replace(url, "//", "/");
		url2 = url2.replaceAll("//", "/").replaceAll(":/", "://");
		return url2;
	}

	@SuppressWarnings("unchecked")
	public static String appendParams2Url(String url, Entry<String, Object>... entries) {
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		for (int i = 0; i < entries.length; i++) {
			if (i == 0) {
				sb.append("?");
			}
			else {
				sb.append("&");
			}
			sb.append(entries[i].getKey()).append("=").append(entries[i].getValue().toString());
		}
		return sb.toString();
	}
	
	/**
	 * URL编码</br>
	 * 
	 * @param str
	 *            需要编码的字符
	 * @return 编码后的内容
	 */
	public static String encode(String str) {
		if (str == null) {
			return null;
		}
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * URL解码</br>
	 * 
	 * @param str
	 *            需要解码的内容
	 * @return 解码后的内容
	 */
	public static String decode(String str) {
		if (str == null) {
			return null;
		}
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// public static void main(String[] args) {
	// String url = "http://www.baidu.com:8080/abc/index.do";
	// System.err.println(hostOfUrl(url));
	// String url1 = "http://www.baidu.com:8080/a/";
	// String url2 = "/b/c/a.do";
	// System.err.println(appendUriMethod2Url(url1, url2));
	//
	// }
}
