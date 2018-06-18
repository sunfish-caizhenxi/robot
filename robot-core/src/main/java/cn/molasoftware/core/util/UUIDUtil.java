/**
 * 
 */
package cn.molasoftware.core.util;
import java.util.UUID;

/**
 * 
 * @author caizhenxi
 *
 */
public class UUIDUtil {
	/**
	 * 生成唯一序列号
	 */
	public static String getUUID() {
		String id = StrUtil.replace(UUID.randomUUID().toString(), "-", "");
		return id;
	}
	/**
	 * 通过获取当前时间戳生成唯一的id
	 */
	public static long getUid(){
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
		System.out.println(System.currentTimeMillis());
       
	}
}
