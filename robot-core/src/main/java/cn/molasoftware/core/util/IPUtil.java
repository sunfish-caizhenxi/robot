package cn.molasoftware.core.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
	/**
	 * 获取代理服务器IP. .
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIP(HttpServletRequest request) {
		if(request == null){
			return "";
		}
		String proxyIp = request.getHeader("X-Real-IP");
		if (proxyIp == null) {
			proxyIp = request.getHeader("RealIP");
		}
		if (proxyIp == null) {
			proxyIp = request.getRemoteAddr();
		}
		return proxyIp;
	}
}
