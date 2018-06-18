package cn.molasoftware.core.util;

/**
 * Resin相关方法类.
 * <p>
 * 
 * @author caizx
 * 
 */

public final class JettyUtil {

	/**
	 * 设置Jetty标识
	 * 
	 * @param isJetty
	 */
	public static void setJetty(boolean isJetty) {
		IS_JETTY = isJetty;
	}

	private static boolean IS_JETTY = false;

	/**
	 * 判断是否为Jetty
	 * 
	 * @return boolean
	 */
	public static boolean isJetty() {
		return IS_JETTY;
	}

}
