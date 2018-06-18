package cn.molasoftware.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具类
 * 
 * @author luxh
 * 
 */
public class HttpUtil {

	static {
		HttpUtil.setAllowRestrictedHeaders(true);
	}

	/**
	 * 打开Http Header安全限制</br>
	 * 
	 * @param allowRestrictedHeaders
	 *            true
	 */
	public static void setAllowRestrictedHeaders(boolean allowRestrictedHeaders) {
		System.setProperty("sun.net.http.allowRestrictedHeaders", allowRestrictedHeaders + "");
	}

	public static String doGet(String url) {
		return doGet(url, -1, -1);
	}

	public static String doPost(String url, Map<String, String> map) {
		return doPost(url, map, -1, -1);
	}

	public static String doPost(String url, String json) {
		String msg = "";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		try {
			StringEntity str = new StringEntity(json, "utf-8");
			// System.out.println(Json.toJson(msgText));
			str.setContentType("application/json");
			post.setEntity(str);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				msg = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 发送 get请求</br>
	 * 
	 * @param url
	 *            请求的url
	 * @param host
	 *            域名
	 * @param connectTimeout
	 *            连接超时时间
	 * @param readTimeout
	 *            请求超时时间
	 * @return 请求的结果
	 */
	public static String doGet(String url, long connectTimeout, long readTimeout) {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			URL oUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) oUrl.openConnection();

			if (connectTimeout > 0) {
				conn.setConnectTimeout((int) connectTimeout);
			}
			if (readTimeout > 0) {
				conn.setReadTimeout((int) readTimeout);
			}
			// System.setProperty("sun.net.http.allowRestrictedHeaders",
			// "true");
			conn.setRequestMethod("GET");

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String str;
			while ((str = in.readLine()) != null) {
				if (sb.length() > 0) {
					sb.append('\n');
				}
				sb.append(str);
			}
			in.close();
			conn.disconnect();
		} catch (SocketTimeoutException e) {

			throw new RuntimeException(e.getMessage() + " timeout:" + readTimeout, e);
		} catch (IOException e) {

			throw new RuntimeException(e.getMessage(), e);
		}
		return sb.toString();
	}

	/**
	 * 发送 post请求</br>
	 * 
	 * @param url
	 *            请求的url
	 * @param map
	 *            参数
	 * @param connectTimeout
	 *            连接超时时间
	 * @param readTimeout
	 *            请求超时时间
	 * @return 请求的结果
	 */
	public static String doPost(String url, Map<String, String> map, long connectTimeout, long readTimeout) {// NOPMD

		StringBuilder param = new StringBuilder();
		if (map != null) {
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				String key = entry.getKey();
				String value = entry.getValue();
				if (param.length() > 0) {
					param.append('&');
				}
				if (value == null) {
					continue;
				}
				param.append(key).append('=').append(UrlUtil.encode(value));
			}
		}
		// System.out.println(param.toString());
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			// System.setProperty("sun.net.http.allowRestrictedHeaders",
			// "true");

			URL postURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();

			if (connectTimeout > 0) {
				conn.setConnectTimeout((int) connectTimeout);
			}
			if (readTimeout > 0) {
				conn.setReadTimeout((int) readTimeout);
			}
			// conn.setRequestProperty("Host", host);

			conn.setUseCaches(false); // do not use cache
			conn.setDoOutput(true); // use for output
			conn.setDoInput(true); // use for Input

			conn.setRequestMethod("POST"); // use the POST method to submit the

			PrintWriter out = new PrintWriter(conn.getOutputStream());

			out.print(param.toString()); // send to server
			out.close(); // close outputstream

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String aline;
			while (null != (aline = in.readLine())) {
				sb.append(aline).append('\n');
			}
			in.close();
			conn.disconnect();
		} catch (SocketTimeoutException e) {

			throw new RuntimeException(e.getMessage() + " timeout:" + readTimeout, e);
		} catch (IOException e) {

			throw new RuntimeException(e.getMessage(), e);
		}
		return sb.toString();
	}

}
