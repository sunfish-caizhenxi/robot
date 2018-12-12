package cn.molasoftware.dao.robot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

/**
 * 
 * @author caizhenxi
 *
 */
public class TuLingRobot {
	private String APIKEY = "32244407fa1f4aae85ba048ba0ec2c0d";
	private String getURL = "http://www.tuling123.com/openapi/api?";
	    //1.调用图灵机器人：方法一
		public  String getByTulingApi1(String quesiton) throws IOException {
	        //接入机器人，输入问题
	        String INFO = URLEncoder.encode(quesiton, "utf-8");//这里可以输入问题
	        getURL += "key="+APIKEY + "&info=" + INFO;
	        URL getUrl = new URL(getURL);
	        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	        System.out.println(connection);
	        connection.connect();

	        // 取得输入流，并使用Reader读取
	        BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
	        StringBuffer sb = new StringBuffer();
	        String line = "";
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
	        reader.close();
	        // 断开连接
	        connection.disconnect();
	        String s = sb.toString();
	        String answer="";
	        JSONObject jsonObj = new JSONObject();
	        jsonObj = JSONObject.fromObject(s);
	        if(jsonObj != null){
	        	answer = jsonObj.getString("text");
	        }
			System.out.println(answer);
			return answer;
	       
	  }
		
		//2.调用图灵机器人:方法二
		public String  getByTulingApi2(String question) {      
			       String answer= "";
				try {
					 String INFO = URLEncoder.encode(question, "utf-8");
					 String requestUrl =   getURL += "key="+ APIKEY + "&info=" + INFO; 
					 HttpGet request = new HttpGet(requestUrl);  
					 HttpResponse response = HttpClients.createDefault().execute(request); 
					 String result = "";
				     //200即正确的返回码  
				    if(response.getStatusLine().getStatusCode()==200){  
				           result = EntityUtils.toString(response.getEntity());  
				           JSONObject jsonObj = new JSONObject();
				           jsonObj = JSONObject.fromObject(result);
				           if(jsonObj != null){
				        	   answer = jsonObj.getString("text");
					           System.out.println("返回结果："+answer);  
				           }
				        }  
				} catch (Exception e) {
					e.printStackTrace();
				}  
			       return answer;
			     
			    }
		
		
		
	

}
