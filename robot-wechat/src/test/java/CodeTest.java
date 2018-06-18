import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class CodeTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String APPID = "wx67b382b3140c57ba";
		String REDIRECT_URI = "http://e68497b5.ngrok.io/wechat/student/getAccessToken";
		String response_type = "code";
		String scope = "snsapi_userinfo";
		String STATE = "1";
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+APPID
				+ "&redirect_uri="+URLEncoder.encode(REDIRECT_URI,"utf-8")
				+ "&response_type="+response_type
				+ "&scope="+scope
				+ "&state="+STATE
				+ "#wechat_redirect";
		System.out.println("url:=>"+url);
		
		
		
	}

}
