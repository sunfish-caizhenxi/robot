package cn.molasoftware.core.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by SHAOFENG on 2017年3月31日 0031.
 */
public final class StringUtil {

    public static boolean isEmpty(String str) {
        if (str != null) {
            str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotNull(String str) {
        return !isEmpty(str);
    }
    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     * @param str	字符串
     * @return
     */
    public static String[] splitString(String str){
        return splitString(str,",\\s*");
    }

    public static String[] splitString(String value, char splitChar) {
        if (isEmpty(value)) {
            return null;
        }
        return StringUtils.split(value, splitChar);
    }

    public static String[] splitString(String value, String splitStr) {
        if (isEmpty(value)) {
            return null;
        }
        return StringUtils.split(value, splitStr);
    }
}
