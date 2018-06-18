package cn.molasoftware.core.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionUtil {
	
	public static double opera(String opera) {
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        Double d = new Double(0);
        try {
        	Object result = engine.eval(opera);
        	if(result==null){
        		return 0;
        	}
        	if(result instanceof Integer){
        		d = ((Integer) result).doubleValue();
        	}else if(result instanceof Long){
        		d = ((Long) result).doubleValue();
        	}else if(result instanceof Short){
        		d = ((Short) result).doubleValue();
        	}else if(result instanceof Float){
        		d = ((Float) result).doubleValue();
        	}else if(result instanceof Double){
                d = (Double) result;
        	}else{
        		d = Double.parseDouble(result.toString());
        	}
        } catch (ScriptException ex) {
        	ex.printStackTrace();
        }
        return d==null||d.isNaN()?0:d.doubleValue();
	}

	public static void main(String args[]) {
		String str = "var qdCMM = 477290.76+0.0+0.0+0.0+0.0+0.0+0.0+0.0+0.0;/**清单计量小计上期完成金额**/"+
"var qdPTMM = 0.0+0.0+0.0+0.0+0.0+0.0+0.0+0.0+0.0;/**清单计量小计上期末完成金额**/"+
"var qdCTMM = qdCMM + qdPTMM;/**清单计量小计本期末完成金额**/"+
"var nmggzbzjqkdMoney = 0.0;/**农民工工资保证金起扣点**/"+
"var nmggzbzjljxeMoney = 5.0E7;/**农民工工资保证金累计扣回限额**/"+
"var nmggzbzjblPercent = 1.5;/**农民工工资保证金扣回比例**/"+
"var nmggzbzjPTMM = 0.0;/**农民工工资保证金到上期末完成金额**/"+
"var nmggzbzjCMM = 0;/**农民工工资保证金到本期完成金额**/"+
"var cutNumber = Math.pow(10, 2);"+
"if(qdCTMM>=nmggzbzjqkdMoney){"+
"        /**清单计量小计本期末完成金额 大于等于 农民工工资保证金起扣点，农民工工资保证金到本期完成金额 = 清单计量小计本期末完成金额*扣回比例 － 农民工工资保证金到上期末完成金额**/"+
"        nmggzbzjCMM = Math.round((qdCTMM*nmggzbzjblPercent/100) * cutNumber)/cutNumber - nmggzbzjPTMM;"+
"}"+
"if(nmggzbzjCMM+nmggzbzjPTMM>=nmggzbzjljxeMoney){"+
"        /**本期末累计扣农民工工资保证金大于等于累计扣农民工工资保证金限额，本期支付=累计扣农民工工资保证金限额 －上期末扣回**/"+
"        nmggzbzjCMM = Math.round((nmggzbzjljxeMoney-nmggzbzjPTMM) * cutNumber)/cutNumber - nmggzbzjPTMM;"+
"}";
		try {
			double a = ExpressionUtil.opera(str);
			System.out.println("答案是：" + a);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
