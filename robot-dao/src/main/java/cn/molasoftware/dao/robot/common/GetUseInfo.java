package cn.molasoftware.dao.robot.common;

import java.util.HashMap;

import cn.molasoftware.dao.robot.util.GlobalConstants;
import cn.molasoftware.dao.robot.util.HttpUtils;
import net.sf.json.JSONObject;

/**
 * ClassName: GetUseInfo
 * @Description: 获取微信用户信息
 * @author dapengniao
 * @date 2016年3月18日 下午2:00:52
 */
public class GetUseInfo {
	/**
	 * @Description: 通过openid获取用户微信信息
	 * @param @param openid
	 * @param @return
	 * @param @throws Exception   
	 * @author dapengniao
	 * @date 2016年3月18日 下午2:01:30
	 */
	public static HashMap<String, String> Openid_userinfo(String openid)
			throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",
				GlobalConstants.getInterfaceUrl("access_token"));  //定时器中获取到的token
		params.put("openid", openid);  //需要获取的用户的openid
		params.put("lang", "zh_CN");
		String subscribers = HttpUtils.sendGet(
				GlobalConstants.getInterfaceUrl("OpenidUserinfoUrl"), params);
		System.out.println(subscribers);
		params.clear();
		//这里返回参数只取了昵称、头像、和性别
		params.put("nickname",
				JSONObject.fromObject(subscribers).getString("nickname")); //昵称
		params.put("headimgurl",
				JSONObject.fromObject(subscribers).getString("headimgurl"));  //图像
		params.put("sex", JSONObject.fromObject(subscribers).getString("sex"));  //性别
		params.put("language", JSONObject.fromObject(subscribers).getString("language"));//语言
		params.put("city", JSONObject.fromObject(subscribers).getString("city"));//城市
		params.put("province", JSONObject.fromObject(subscribers).getString("province"));//省份
		params.put("country", JSONObject.fromObject(subscribers).getString("country"));//国家
		params.put("headimgurl", JSONObject.fromObject(subscribers).getString("headimgurl"));//国家
		return params;
	}

}
