package cn.molasoftware.robot.wechat.controller.student;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.molasoftware.dao.robot.pojo.Student;
import cn.molasoftware.dao.robot.util.HttpUtils;
import cn.molasoftware.service.robot.student.StudentService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public Student get(){
		Student student = studentService.selectByPrimaryKey(1);
		return  student;
	}
	@RequestMapping(value="/getAccessToken",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView getAccessToken(String code,int state){
		String respStr = "";
		String APPID = "wx67b382b3140c57ba";
		String SECRET = "f8e764d3b91acc0affb21ddc92f336e0";
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		Map<String, String> params = new HashMap<String, String>();
		if(state == 1){
			params.put("appid", APPID);
			params.put("secret", SECRET);
			params.put("code", code);
			params.put("grant_type","authorization_code");
			try {
				respStr = HttpUtils.sendPost(url, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(respStr);
		JSONObject jsonObj = JSONObject.fromObject(respStr);
		String openId = jsonObj.getString("openid");
		String access_token = jsonObj.getString("access_token");
		String url2 = "https://api.weixin.qq.com/sns/userinfo";
		params.clear();
		try {
			params.put("openid", openId);
			params.put("access_token", access_token);
			params.put("lang", "zh_CN");
			respStr = HttpUtils.sendGet(url2, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(respStr);
		return new ModelAndView(respStr);
	}

}
