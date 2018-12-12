package cn.molasoftware.dao.robot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {
	/**
	 * 获取订单编号
	 * @param prefix
	 * @return
	 */
	public static String getOrderNum(String prefix){
		return prefix+new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
	}

}
