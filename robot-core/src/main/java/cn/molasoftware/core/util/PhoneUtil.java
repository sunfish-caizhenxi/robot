package cn.molasoftware.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class PhoneUtil {
	private static String PATTERN_FOR_USER_AGENT_START_WITH_MOZILLA="\\((^\\(||^\\)|\\S|\\s)+\\)";

	public static String getPhone(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		return getPhone(userAgent);
	}
	
	private static String getPhone(String userAgent){
		if(StrUtil.isBlank(userAgent)){
			return "";
		}
		if(userAgent.toLowerCase().indexOf("iphone")!=-1){
			return "iphone";
		}else if(userAgent.toLowerCase().indexOf("ipad")!=-1){
			return "ipad";
		}else if(userAgent.toLowerCase().startsWith("mozilla")){
			Pattern pattern = Pattern.compile(PATTERN_FOR_USER_AGENT_START_WITH_MOZILLA);  
	        Matcher matcher = pattern.matcher(userAgent);  
	        String model = "";  
	        if (matcher.find()) {  
	            model = matcher.group().trim();  
		        model = StrUtil.replace(model, "Build", "");
		        int beginIndex = model.lastIndexOf(";");
		        int endIndex = model.indexOf("/");
		        if(beginIndex > endIndex || endIndex == -1){
		        	model = model.substring(beginIndex+1, model.length());
		        }else{
		        	model = model.substring(beginIndex+1, endIndex);
		        }
	        }
	        return model.trim();
		}else{
			int endIndex = userAgent.indexOf("Android");
			if(endIndex == -1){
				return "";
			}else{
				String model = userAgent.substring(0, endIndex);
				return model.trim();
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(getPhone("Mozilla/5.0 (Linux; U; Android 4.1.2; zh-CN; Coolpad 5891 Build/JZO54K) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/9.9.3.478 U3/0.8.0 Mobile Safari/533.1"));
		System.out.println(getPhone("Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11B554a Safari/9537.53"));
		System.out.println(getPhone("Mozilla/5.0 (Linux; Android 4.3; zh-cn; SAMSUNG-GT-I9308_TD/1.0 Android/4.3 Release/11.15.2013 Browser/AppleWebKit534.30 Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"));
		System.out.println(getPhone("Huawei U8800    Android 2.3.3   UC 8.7  Mozilla/5.0 (Linux; U; Android 2.3.5; zh-cn; U8800 Build/HuaweiU8800) UC AppleWebKit/534.31 (KHTML, like Gecko) Mobile Safari/534.31"));
		System.out.println(getPhone("Meizu MX M031   Android 4.0.3   Opera 12.1  Opera/9.80 (Android 4.0.3; Linux; Opera Mobi/ADR-1210241511) Presto/2.11.355 Version/12.10"));
		System.out.println(getPhone("Meizu MX M031   Android 4.0.3   Baidu 2.2   Mozilla/5.0 (Linux; U; Android 4.0.3; zh-cn) AppleWebKit/530.17 (KHTML, like Gecko) FlyFlow/2.2 Version/4.0 Mobile Safari/530.17"));
		System.out.println(getPhone("Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; R8007 Build/JLS36C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"));
	}
}
