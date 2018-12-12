package cn.molasoftware.core.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {
	/**
	 * 获取代理服务器IP. .
	 * 
	 * @param request
	 * @return
	 */
	public static String getProxyIp(HttpServletRequest request) {
		String proxyIp = request.getHeader("X-Real-IP");
		if (proxyIp == null) {
			proxyIp = request.getHeader("RealIP");
		}
		if (proxyIp == null) {
			proxyIp = request.getRemoteAddr();
		}
		return proxyIp;
	}

	public static String getRequestContextUri(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI;
		if ("/".equals(contextPath)) {
			requestURI = request.getRequestURI();
		} else {
			String uri = request.getRequestURI();
			requestURI = uri.substring(contextPath.length());
		}
		if (requestURI.indexOf("//") != -1) {
			requestURI = requestURI.replaceAll("/+", "/");
		}
		return requestURI;
	}

	/**
	 * 获取请求的URL，包含参数
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		if (StrUtil.isNotBlank(request.getQueryString())) {
			url.append("?");
			url.append(request.getQueryString());
		}
		return url.toString();
	}

	public static HttpServletRequest getCurrentRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
}
