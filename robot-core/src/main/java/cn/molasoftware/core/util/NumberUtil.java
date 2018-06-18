package cn.molasoftware.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NumberUtil {	
    private static Log log = LogFactory.getLog(NumberUtil.class);
	private static final String zhnum_0 = "零壹贰叁肆伍陆柒捌玖";
	private static final String zhnum = "零一二三四五六七八九";
	private static final String[] zhnum1 = {"","十","百","千"};
	private static final String[] zhnum1_0 = {"","拾","佰","仟"};
	private static final String[] zhnum2 = {"","万","亿","万亿","亿亿"};
    private static final int DEFAULT_PRICESION = 3;

	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	/**
	 * 判断是否大于0
	 * 
	 * @param num
	 *            Double对象
	 * @return boolean
	 */
	public static boolean isGreaterZero(Number num) {
		if (num == null) {
			return false;
		}
		return num.doubleValue() > 0;
	}

	/**
	 * 返回int值，null返回0
	 * 
	 * @param num 
	 * @return 
	 */
	public static int toInt(Number num) {
		if (num == null) {
			return 0;
		}
		else {
			return num.intValue();
		}
	}

	/**
	 * 返回int值，null返回默认值
	 * 
	 * @param num 
	 * @param def 默认值
	 * @return 
	 */
	public static int toInt(Number num, int def) {
		if (num == null) {
			return def;
		}
		else {
			return num.intValue();
		}
	}
	/**
	 * 返回short值，null返回0
	 * 
	 * @param num 
	 * @return 
	 */
	public static short toShort(Number num) {
		if (num == null) {
			return 0;
		}
		else {
			return num.shortValue();
		}
	}

	/**
	 * 返回short值，null返回默认值
	 * 
	 * @param num 
	 * @param def 默认值
	 * @return 
	 */
	public static short toShort(Number num, short def) {
		if (num == null) {
			return def;
		}
		else {
			return num.shortValue();
		}
	}
	/**
	 * 返回float值，null返回0
	 * 
	 * @param num
	 * @return
	 */
	public static float toFloat(Number num) {
		if (num == null) {
			return 0;
		}
		else {
			return num.floatValue();
		}
	}

	/**
	 * 返回float值，null返回默认值
	 * 
	 * @param num  
	 * @param def 默认值
	 * @return
	 */
	public static float toFloat(Number num, float def) {
		if (num == null) {
			return def;
		}
		else {
			return num.floatValue();
		}
	}
	
	/**
	 * 返回double值，null返回0
	 * 
	 * @param num
	 * @return
	 */
	public static double toDouble(Number num) {
		if (num == null) {
			return 0;
		}
		else {
			return num.floatValue();
		}
	}

	/**
	 * 返回double值，null返回默认值
	 * 
	 * @param num  
	 * @param def 默认值
	 * @return
	 */
	public static double toDouble(Number num, double def) {
		if (num == null) {
			return def;
		}
		else {
			return num.doubleValue();
		}
	}
	
	/**
	 * 返回long值，null返回0
	 * 
	 * @param num
	 * @return
	 */
	public static long toLong(Number num) {
		if (num == null) {
			return 0;
		}
		else {
			return num.longValue();
		}
	}

	/**
	 * 返回long值，null返回默认值
	 * 
	 * @param num
	 * @param def 默认值
	 * @return
	 */
	public static long toLong(Number num, int def) {
		if (num == null) {
			return def;
		}
		else {
			return num.longValue();
		}
	}
	
    /**
     * 把字符串解析成short类型的值
     * @param pValue
     * @return
     */
    public static short parseShort(String pValue) {
        if(pValue == null) return 0;
        try {
            return Short.parseShort(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    /**
     * 把字符串解析成short类型的值
     * @param pValue
     * @return
     */
    public static Short parseShortObj(String pValue) {
        if(pValue == null) return null;
        try {
            return new Short(Short.parseShort(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    /**
     * 把字符串解析成int类型的值
     * @param pValue
     * @return
     */
    public static int parseInt(String pValue) {
        if(pValue == null) return 0;
        try {
            return Integer.parseInt(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    /**
     * 把字符串解析成int类型的值
     * @param pValue
     * @return
     */
    public static Integer parseInteger(String pValue) {
        if(pValue == null) return null;
        try {
            return new Integer(Integer.parseInt(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    /**
     * 把字符串解析成long类型的值
     * @param pValue
     * @return
     */
    public static long parseLong(String pValue) {
        if(pValue == null) return 0;
        try {
            return Long.parseLong(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    /**
     * 把字符串解析成long类型的值
     * @param pValue
     * @return
     */
    public static Long parseLongObj(String pValue) {
        if(pValue == null) return null;
        try {
            return new Long(Long.parseLong(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    /**
     * 把字符串解析成double类型的值
     * @param pValue
     * @return
     */
    public static double parseDouble(String pValue) {
        if(pValue == null) return 0;
        try {
            return Double.parseDouble(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    /**
     * 把字符串解析成double类型的值
     * @param pValue
     * @return
     */
    public static Double parseDoubleObj(String pValue) {
        if(pValue == null) return null;
        try {
            return new Double(Double.parseDouble(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

	/**
	 * 格式化十进制数，分隔千位
	 * 
	 * @param num 十进制数
	 * @return 格式化后的数值
	 */
	public static String format(double num) {
		return format(num, ",###");
	}
	
    /**
     * 按给定的格式格式化
     * @param d
     * @param pattern
     * @return
     */
    public static String format(double d, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }

    /**
     * 按给定的格式格式化
     * @param d
     * @param pattern
     * @param returnZero 是否返回0
     * @return
     */
    public static String format(double d, String pattern, boolean returnZero) {
    	if(!returnZero&&d==0){
    		return "";
    	}
    	return format(d, pattern);
    }
    
    /**
     * 按给定的格式格式化
     * @param d
     * @param pattern
     * @return
     */
    public static String format(Double d, String pattern) {
        if(d != null) {
            return format(d.doubleValue(), pattern);
        }else {
            return null;
        }
    }
    /**
     * 按给定的格式格式化
     * @param d
     * @param pattern
     * @param returnZero 是否返回0
     * @return
     */
    public static String format(Double d, String pattern, boolean returnZero) {
    	if(!returnZero&&d==0){
    		return "";
    	}
    	return format(d, pattern);
    }
    
    /**
     * 指定精度格式化字符串, 不显示末尾多余的0
     * @param d
     * @param precision
     * @return
     */
    public static String format(double d, int precision) {
        if (precision < 0) {
            return String.valueOf(d);
        }
        StringBuffer sb = new StringBuffer("#");
        if (precision > 0) {
            sb.append(".");
        }
        for (int i = 0; i < precision; i++) {
            sb.append("#");
        }
        return format(d, sb.toString());
    }
    
    /**
     * 指定精度格式化字符串, 不显示末尾多余的0
     * @param d
     * @param precision
     * @param returnZero 是否返回0
     * @return
     */
    public static String format(double d, int precision, boolean returnZero) {
    	if(!returnZero&&d==0){
    		return "";
    	}
    	return format(d, precision);
    }
    
    /**
     * 指定精度格式化字符串, 不显示末尾多余的0
     * @param d
     * @param precision
     * @return
     */
    public static String format(Double d, int precision) {
        if(d != null) {
            return format(d.doubleValue(), precision);
        }else {
            return null;
        }
    }

    /**
     * 指定精度格式化字符串, 不显示末尾多余的0
     * @param d
     * @param precision
     * @param returnZero 是否返回0
     * @return
     */
    public static String format(Double d, int precision, boolean returnZero) {
    	if(!returnZero&&d==0){
    		return "";
    	}
    	return format(d, precision);
    }
    
    /**
    * 指定精度格式化字符串, 不删除后面的0。 如果不够用0补齐。   
    * @param d
    * @param precision
    * @return
    */
    public static String forcibleFormat(double d, int precision) {
        if (precision < 0) {
            return String.valueOf(d);
        }
        StringBuffer sb = new StringBuffer("#");
        if (precision > 0) {
            sb.append(".");
        }
        for (int i = 0; i < precision; i++) {
            sb.append("0");
        }
        return format(d, sb.toString());
    }

    /**
     * 指定精度格式化字符串, 不删除后面的0。 如果不够用0补齐。
     * @param d
     * @param precision
     * @param returnZero 是否返回0
     * @return
     */
    public static String forcibleFormat(double d, int precision, boolean returnZero) {
    	if(!returnZero&&d==0){
    		return "";
    	}
    	return forcibleFormat(d, precision);
    }

    /**
    * 指定精度格式化字符串, 不删除后面的0。 如果不够用0补齐。   
    * @param d
    * @param precision
    * @return
    */
    public static String forcibleFormat(Double d, int precision) {
        if(d != null) {
            return forcibleFormat(d.doubleValue(), precision);
        }else {
            return null;
        }
    }

    /**
     * 指定精度格式化字符串, 不删除后面的0。 如果不够用0补齐。
     * @param d
     * @param precision
     * @param returnZero 是否返回0
     * @return
     */
    public static String forcibleFormat(Double d, int precision, boolean returnZero) {
    	if(!returnZero&&d==0){
    		return "";
    	}
    	return forcibleFormat(d, precision);
    }
	/**
     * 四舍五入，保留3位小数
	 * 
	 * @param v
	 * @return
	 */
	public static double round(double d) {
		return round(d, DEFAULT_PRICESION);
	}
	
    /**
     * 四舍五入
     * @param d
     * @param precision
     * @return
     */
    public static double round(double d, int precision) {    	
    	BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
    }

    /**
     * 四舍五入
     * @param d
     * @param precision
     * @return
     */
    public static Double round(Double d, int precision) {
        if(d != null) {
            return new Double(round(d.doubleValue(), precision));
        }else {
            return null;
        }
    }
    
    /**
	 * double 与 double 相加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double... v) {
		BigDecimal vs = null;
		if(v != null && v.length > 1){
			for(double vi : v){
				BigDecimal b = new BigDecimal(Double.toString(vi));
				if(vs == null){
					vs = b;
				}else{
					vs = vs.add(b);
				}
			}
		}
		return vs == null ? 0 : vs.doubleValue();
	}

	/**
	 * double 与 double 相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double... v) {
		BigDecimal vs = null;
		if(v != null && v.length > 1){
			for(double vi : v){
				BigDecimal b = new BigDecimal(Double.toString(vi));
				if(vs == null){
					vs = b;
				}else{
					vs = vs.subtract(b);
				}
			}
		}
		return vs == null ? 0 : vs.doubleValue();
	}

	/**
	 * double 与 double 相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double... v) {
		BigDecimal vs = null;
		if(v != null && v.length > 1){
			for(double vi : v){
				BigDecimal b = new BigDecimal(Double.toString(vi));
				if(vs == null){
					vs = b;
				}else{
					vs = vs.multiply(b);
				}
			}
		}
		return vs == null ? 0 : vs.doubleValue();
	}

	/**
	 * double 与 double 相除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double div(double... v) {
		BigDecimal vs = null;
		if(v != null && v.length > 1){
			for(double vi : v){
				BigDecimal b = new BigDecimal(Double.toString(vi));
				if(vs == null){
					vs = b;
				}else{
					vs = vs.divide(b, DEFAULT_PRICESION, BigDecimal.ROUND_HALF_UP);
				}
			}
		}
		return vs == null ? 0 : vs.doubleValue();
	}

	/**
	 * 在不满足指定位数的数字前面填充0
	 * 
	 * @param number
	 *            数字
	 * @param minlen
	 *            位数
	 * @return String
	 */
	public static String fixed(int number, int minlen) {
		String result = String.valueOf(number);
		while (result.length() < minlen) {
			result = "0" + result;
		}
		return result;
	}
	
	 /**
     * 将 1 组 4 位的数字转换成中文
     * 返回格式为“几千几百几十几”的格式
     */
    private static String numberToZH4(String s,boolean fan){
        StringBuffer sb = new StringBuffer(256);
        if (s.length()!=4) return null;
        for (int i=0;i<4;i++){
            char c1 = s.charAt(i);
            // 去掉重复的“零”
            if (c1=='0' && i>1 && s.charAt(i-1)=='0')  continue;
            if (c1!='0' && i>1 && s.charAt(i-1)=='0') sb.append('零');
            if (c1!='0') {
                if (fan) {
                    //大写
                    sb.append(zhnum_0.charAt(c1-48)); //数字
                    sb.append(zhnum1_0[4-i-1]); // 数量级：千、百、十
                } else {
                    //小写
                    sb.append(zhnum.charAt(c1-48));//数字
                    sb.append(zhnum1[4-i-1]);// 数量级：千、百、十
                }
            }
        }
        return new String(sb);
    }
    
    /**
     * 将一个 long 数字转换为汉字
     *
     * @param n 要转换的 long 类型的数字
     * @param fan 如果此参数true，数字将被转换为中文大写，否则转换为中文小写
     * @return 参数 n 的汉字格式
     */
    public static String numberToZH(long n,boolean fan){
        StringBuffer sb = new StringBuffer(16);
        // 把 long 转换为字符串，且使它的长度是 4 的倍数 
        
        String strN = "000"+(n<0?-n:n);
        int strN_L = strN.length()/4;
        strN = strN.substring(strN.length()-strN_L*4);
        // 中文数字是每 4 位为 1 组，所以，要一组一组地转换。
        for (int i=0;i<strN_L;i++){
            String s1 = strN.substring(i*4,i*4+4);
            String s2 = numberToZH4(s1,fan);
            //转换一组
            sb.append(s2); 
            // 该组的数量级：“亿亿、万亿、亿、万”，由循环变量 i 控制，从 zhnum2 串数组里取得
            if(s2.length()!=0) sb.append(zhnum2[strN_L-i-1]);
        }
        String s = new String(sb);
        // 去掉中文数字的前导“零”
        if (s.length()!=0 && s.startsWith("零"))
            s = s.substring(1);
        if(s.length()==3 && s.charAt(1)=='十' && s.charAt(0)=='一')
        	s = s.substring(s.length()-2);
        if(n<0)
        	s = "负" + s;
        return s;
    }
    /**
     * 将一个 double 数字转换为汉字
     *
     * @param d 要转换的 double 类型的数字
     * @param fan 如果此参数true，数字将被转换为中文大写，否则转换为中文小写
     *@return 参数 d 的汉字格式
     */
    public static String numberToZH(double d,boolean fan){
        return numberToZH("" + d,fan);
    }
    
    /**
     * 将一个字符串数字转换为汉字
     *
     * @param str 要转换的 String 类型的数字字符串
     * @param fan 如果此参数true，数字将被转换为中文大写，否则转换为中文小写
     * @return 参数 str 的汉字格式
     */
    public static String numberToZH(String str,boolean fan){
        StringBuffer sb = new StringBuffer(16);
        int dot = str.indexOf(".");
        if (dot<0) dot=str.length();
        
        // 取出整数部分，由 String numberToZH(long n,boolean fan) 处理
        String zhengshu = str.substring(0,dot);
        sb.append(numberToZH(Long.parseLong(zhengshu),fan));
        
        // 小数部分
        if (dot!=str.length()){
            sb.append("点");
            String xiaoshu = str.substring(dot+1);
            for (int i=0;i<xiaoshu.length();i++){
                if(fan){
                    sb.append(zhnum_0.charAt(Integer.parseInt(xiaoshu.substring(i,i+1))));
                } else {
                    sb.append(zhnum.charAt(Integer.parseInt(xiaoshu.substring(i,i+1))));
                }
            }
        }
        String s = new String(sb);
        if (s.startsWith("零")) s = s.substring(1);
        // 中文小写 以“一十”开头的，去掉前面的“一”，
        // 中文大写 以“壹拾”开头的，不作处理。
        if (s.startsWith("一十")) s = s.substring(1);
        // 去掉末尾的“零”
        while (s.endsWith("零")){s = s.substring(0,s.length()-1);}
        if (s.endsWith("点")) s = s.substring(0,s.length()-1);
        return s;
    }
    
    /**
     * 将一个 double 数字转换为人民币大写的汉字格式
     *
     * @param rmb 要转换的 double 类型的数字
     * @return 参数 rmb 的人民币大写汉字格式
     */
    public static String numberToRMB(double rmb){
        // 这段 不用解释了吧
        String strRMB = "" + rmb;
        DecimalFormat nf = new DecimalFormat("#.#");
        nf.setMaximumFractionDigits(2);
        strRMB=nf.format(rmb).toString();
        strRMB=numberToZH(strRMB,true);
        if (strRMB.indexOf("点")>=0){
            strRMB = strRMB + "零";
            strRMB = strRMB.replaceAll("点","圆");
            String s1 = strRMB.substring(0,strRMB.indexOf("圆")+1);
            String s2 = strRMB.substring(strRMB.indexOf("圆")+1);
            strRMB=s1 + s2.charAt(0) + "角" + s2.charAt(1) + "分整";
        }else {
            strRMB=strRMB+"圆整";
        }
        return "人民币(大写):" + strRMB;
    }

}
