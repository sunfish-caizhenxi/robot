package cn.molasoftware.robot.wechat.handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.molasoftware.dao.robot.common.JSSDK_Config;
import cn.molasoftware.dao.robot.common.Message;



/**
 * ClassName: WeChatController
 * @Description: 前端用户微信配置获取
 * @author dapengniao
 * @date 2016�?3�?19�? 下午5:57:36
 */
@Scope("prototype")
@Controller
@RequestMapping("/wechatconfig")
public class WeChatController {
	private static Logger logger = Logger.getLogger(WeChatController.class);
	/**
	 * @Description: 前端获取微信JSSDK的配置参�?
	 * @param @param response
	 * @param @param request
	 * @param @param url
	 * @param @throws Exception
	 * @author dapengniao
	 * @date 2016�?3�?19�? 下午5:57:52
	 */
	@RequestMapping("jssdk")
	@ResponseBody
	public Message JSSDK_config(
			@RequestParam(value = "url", required = true) String url) {
		try {
			System.out.println(url);
			Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url);
			return Message.success(configMap);
		} catch (Exception e) {
			logger.error(e,e);
			return Message.error();
		}

	}

}
