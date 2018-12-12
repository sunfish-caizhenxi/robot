package cn.molasoftware.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServerUtil {// NOPMD
	private static final Log logger = LogFactory.getLog(ServerUtil.class);

	public ServerUtil() {

	}

	/**
	 * 获取eth0网卡IP地址.
	 * 
	 * @return
	 */
	public static String getServerIp() {
		return ServerIpUtil.getServerIp();
	}

	// /**
	// * 获取本机IP.
	// *
	// * @return
	// */
	// public static String getIp(String[] prefixs) {
	// List<String> list = getHostAddress();
	// String currentIp = null;
	// for (int i = 0; i < prefixs.length; i++) {
	// for (String ip : list) {
	// if (ip.startsWith(prefixs[i])) {
	// currentIp = ip;
	// break;
	// }
	// }
	// if (currentIp != null) {
	// break;
	// }
	// }
	// return currentIp;
	// }

	/**
	 * 根据域名获取指向的IP(无缓存，使用时请注意性能)
	 * 
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public static String getIp(String host) {
		try {
			InetAddress inet = InetAddress.getByName(host);
			String ip = inet.getHostAddress();
			return ip;
		}
		catch (UnknownHostException e) {
			logger.info(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	// /**
	// * 返回IP地址列表
	// *
	// * @return IP地址列表
	// */
	// private static List<String> getHostAddress() {// NOPMD
	// Enumeration<NetworkInterface> netInterfaces;
	// try {
	// netInterfaces = NetworkInterface.getNetworkInterfaces();
	// }
	// catch (SocketException e) {
	// logger.info(e.getMessage(), e);
	// return null;
	// }
	// List<String> list = new ArrayList<String>();
	// while (netInterfaces.hasMoreElements()) {
	// NetworkInterface ni = netInterfaces.nextElement();
	// String displayName = ni.getDisplayName();
	// // System.out.println("getDisplayName:" + ni.getDisplayName());
	// Enumeration<InetAddress> ips = ni.getInetAddresses();
	//
	// List<String> subList = null;
	// if ("eth0".equalsIgnoreCase(displayName)) {
	// Enumeration<NetworkInterface> subInterfaces = ni.getSubInterfaces();
	// subList = getHostAddress(subInterfaces);
	// }
	// String currentIp = null;
	// while (ips.hasMoreElements()) {
	// InetAddress inet = ips.nextElement();
	// String ip = inet.getHostAddress();
	// if (ip.indexOf(":") == -1) {
	// // 不要使用IPv6
	// if (currentIp == null) {
	// currentIp = ip;
	// }
	// else if (subList != null && !subList.contains(ip)) {
	// currentIp = ip;
	// }
	// else {// NOPMD
	// // 忽略
	// }
	// }
	// }
	// if (currentIp != null) {
	// list.add(currentIp);
	// }
	// }
	// return list;
	// }

	// private static List<String> getDefaultHostAddress() {
	// Enumeration<NetworkInterface> netInterfaces;
	// try {
	// netInterfaces = NetworkInterface.getNetworkInterfaces();
	// }
	// catch (SocketException e) {
	// logger.info(e.getMessage(), e);
	// return null;
	// }
	// return getHostAddress(netInterfaces);
	// }

	// /**
	// * 根据网络接口返回IP地址列表
	// *
	// * @param netInterfaces
	// * 网络接口
	// * @return IP地址列表
	// */
	// private static List<String> getHostAddress(Enumeration<NetworkInterface> netInterfaces) {
	// List<String> list = new ArrayList<String>();
	// // System.out.println("getHostAddress:" + netInterfaces);
	//
	// while (netInterfaces.hasMoreElements()) {
	// NetworkInterface ni = netInterfaces.nextElement();
	// // System.out.println("getDisplayName:" + ni.getDisplayName());
	// Enumeration<InetAddress> ips = ni.getInetAddresses();
	// while (ips.hasMoreElements()) {
	//
	// InetAddress inet = ips.nextElement();
	// String ip = inet.getHostAddress();
	//
	// if (ip.indexOf(":") == -1) {
	// // 不要使用IPv6
	// list.add(ip);
	// // System.out.println("ip:" + ip);
	// }
	// }
	// }
	// return list;
	// }

	// public static void test() {
	// if (true) {
	// String serverIp = ServerUtil.getIp("server");
	// System.out.println("serverIp:" + serverIp);
	// }
	// if (true) {
	// String[] prefixs = { "113.106.", "218.30.", "112.91.", "121.9.",
	// "192.168.", "120.132.133." };
	// String ip = ServerUtil.getIp(prefixs);
	// System.out.println("ip:" + ip);
	// }
	// List<String> list = ServerUtil.getHostAddress();
	// for (String ip : list) {
	// System.out.println(ip);
	// }
	// }

	// public static void main(String[] args) {
	// if (true) {
	// ServerUtil.test();
	// return;
	// }
	// // run com.duowan.utils.ServerUtil
	// // String ip = ServerUtil.getIp();
	// // Systemout.println("ip:" + ip);
	// }

}
