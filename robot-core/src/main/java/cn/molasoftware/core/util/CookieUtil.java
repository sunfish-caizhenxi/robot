package cn.molasoftware.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * cookie操作
 * 
 * @author caizx
 */
public class CookieUtil {
	protected static final Log logger = LogFactory.getLog(CookieUtil.class);

	/**
	 * 设置cookie</br>
	 * 
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 */
	public static void setCookie(String name, String value, HttpServletRequest request, HttpServletResponse response) {
		int maxAge = -1;
		CookieUtil.setCookie(name, value, maxAge, request, response);
	}

	/**
	 * 设置cookie</br>
	 * 
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            最大生存时间
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 */
	public static void setCookie(String name, String value, int maxAge, HttpServletRequest request, HttpServletResponse response) {
		boolean httpOnly = false;
		CookieUtil.setCookie(name, value, maxAge, httpOnly, request, response);
	}

	/**
	 * 设置cookie</br>
	 * 
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            最大生存时间
	 * @param httpOnly
	 *            cookie的路径
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 */
	// public static void setCookie(String name, String value, int maxAge,
	// boolean httpOnly, HttpServletRequest request, HttpServletResponse
	// response) {
	// boolean currentDomain = true;
	// CookieUtil.setCookie(name, value, maxAge, httpOnly, currentDomain,
	// request, response);
	// }
	//
	// /**
	// * 设置cookie</br>
	// *
	// * @param name
	// * cookie名称
	// * @param value
	// * cookie值
	// * @param maxAge
	// * 最大生存时间
	// * @param httpOnly
	// * cookie的路径
	// * @param currentDomain
	// * 是否使用当前的域名
	// * @param request
	// * http请求
	// * @param response
	// * http响应
	// */
	public static void setCookie(String name, String value, int maxAge, boolean httpOnly, HttpServletRequest request, HttpServletResponse response) {
		String domain = request.getServerName();
		// String domain = serverName;
		setCookie(name, value, maxAge, httpOnly, domain, response);
	}

	public static void setCookie(String name, String value, int maxAge, boolean httpOnly, String domain, HttpServletResponse response) {
		AssertUtil.assertNotBlank(name, "cookie名称不能为空.");
		AssertUtil.assertNotNull(value, "cookie值不能为空.");

		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(domain);
		cookie.setMaxAge(maxAge);
		if (httpOnly) {
			// TODO ahai httponly改动未测试.
			cookie.setPath("/");
			//cookie.setHttpOnly(true);
		}
		else {
			cookie.setPath("/");
		}
		// TODO ahai 所有cookie都要写这些值吗?
		response.addHeader("P3P", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie的值</br>
	 * 
	 * @param name
	 *            cookie名称
	 * @param request
	 *            http请求
	 * @return cookie值
	 */
	public static String getCookie(String name, HttpServletRequest request) {
		// if (StringUtils.isEmpty(name)) {
		// throw new NullPointerException("cookie名称不能为空.");
		// }
		AssertUtil.assertNotBlank(name, "cookie名称不能为空.");

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equalsIgnoreCase(cookies[i].getName())) {
				return cookies[i].getValue();
			}
		}
		return null;
	}

	/**
	 * 获取yygVer的版本</br>
	 * 
	 * @param request
	 *            http请求
	 * @return yygVer的版本
	 */
	public static float getYygVersion(HttpServletRequest request) {
		String yygVer = CookieUtil.getCookie("yygVer", request);
		return CookieUtil.getYygVersion(yygVer);
	}

	/**
	 * 获取yygVer的版本</br>
	 * 
	 * @param yygVer
	 *            yygVer版本字符串
	 * @return yygVer的版本
	 */
	public static float getYygVersion(String yygVer) {
		yygVer = StringUtils.trim(yygVer);
		if (StringUtils.isEmpty(yygVer)) {
			return -1;
		}

		int firstIndex = yygVer.indexOf('.');
		int secondIndex = yygVer.indexOf('.', firstIndex + 1);
		String prefix = yygVer.substring(0, secondIndex);
		String version = prefix.replace("(", "");
		return Float.parseFloat(version);
	}

	/**
	 * 获取yygVer的版本，用正则表达式匹配</br>
	 * 
	 * @param yygVer
	 *            yygVer版本字符串
	 * @return yygVer的版本
	 */
	// public static float getYygVersion_bak(String yygVer) {
	// yygVer = StringUtils.trim(yygVer);
	// if (StringUtils.isEmpty(yygVer)) {
	// return -1;
	// }
	// // System.out.println("yygVer:" + yygVer);
	// String regex = "\\(([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)\\)";
	// Pattern p = Pattern.compile(regex);
	// Matcher m = p.matcher(yygVer);
	// if (m.find()) {
	// int num1 = Integer.parseInt(m.group(1));
	// int num2 = Integer.parseInt(m.group(2));
	// // int num3 = Integer.parseInt(m.group(3));
	// // int num4 = Integer.parseInt(m.group(4));
	// String value = num1 + "." + num2;
	// return Float.parseFloat(value);
	// }
	// return -1;
	// }

	/**
	 * 删除cookie</br>
	 * 
	 * @param name
	 *            cookie名称
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 */
	public static void deleteCookie(String name, HttpServletRequest request, HttpServletResponse response) {
		// if (StringUtils.isEmpty(name)) {
		// throw new NullPointerException("cookie名称不能为空.");
		// }
		AssertUtil.assertNotBlank(name, "cookie名称不能为空.");
		CookieUtil.setCookie(name, "", -1, false, request, response);
	}

	/**
	 * 删除cookie</br>
	 * 
	 * @param name
	 *            cookie名称
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 */
	public static void deleteCookie(String name, String domain, HttpServletResponse response) {
		// if (StringUtils.isEmpty(name)) {
		// throw new NullPointerException("cookie名称不能为空.");
		// }
		AssertUtil.assertNotBlank(name, "cookie名称不能为空.");
		CookieUtil.setCookie(name, "", -1, false, domain, response);
	}
	// /**
	// * 判断字符串是否为空</br>
	// *
	// * @param str
	// * 字符串
	// * @return 字符串为空则返回true
	// */
	// private static boolean isEmpty(String str) {
	// return (str == null || str.length() == 0);
	// }

	// public static void main(String[] args) {
	// // String serverName = "udb.sc2.com.cn";
	// String serverName = "udb.duowan.cn";
	// String domain = CookieUtil.getDomain(serverName);
	// System.out.println("domain:" + domain);
	// }

}
