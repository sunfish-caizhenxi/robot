package cn.molasoftware.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型常用方法.
 * 
 * @author caizx
 * 
 */
public class GenericUtil {
	public static final int IS_INTEGER = 1;
	public static final int IS_STRING = 2;
	public static final int IS_LIST = 3;
	public static final int IS_LONG = 4;

	public static final int IS_MODEL = 99;

	/**
	 * 获取对象直接超类的实际类型参数的 Type对象的数组
	 * 
	 * @param obj
	 *            对象
	 * @return 表示参数化类型的实际类型参数的 Type对象的数组
	 */
	public static Type[] getActualTypeArguments(Object obj) {
		Type genericType = obj.getClass().getGenericSuperclass();
		if (genericType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericType;
			return type.getActualTypeArguments();
		}
		else {
			throw new IllegalArgumentException("非法genericType:" + genericType);
		}
	}

	// @SuppressWarnings("unchecked")
	// /**
	// * 普通的非泛型类Class </br>
	// * 泛型化的类Class<T> </br>
	// * JDK中，普通的Class.newInstance()方法的定义返回Object，要将该返回类型强制转换为另一种类型; </br>
	// * 但是使用泛型的Class<T>，Class.newInstance()方法具有一个特定的返回类型; </br>
	// * @param clazz
	// * @param index
	// * @return
	// */
	// public static <T> Class<T> newInstance(Class<?> clazz, int index) {
	// Object param = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
	// Class<T> result = null;
	// try {
	// result = (Class<T>) param;
	// } catch (Exception e) {
	// String className = param.toString();
	// if (className.startsWith("java.util.List")) {
	// className = "java.util.List";
	// }
	// try {
	// result = (Class<T>) Class.forName(className);
	// } catch (ClassNotFoundException e1) {
	// throw new RuntimeException(e1.getMessage(), e1);
	// }
	// }
	// return result;
	// }

	// /**
	// * 获取classtype</br>
	// *
	// * @param clazz
	// * 类
	// * @return 类型
	// */
	// public static int getClassType(Class<?> clazz) {
	// String className = clazz.getName();
	// // System.out.println("className:" + className);
	// if (className.equals("java.lang.Integer")) {
	// return IS_INTEGER;
	// }
	//
	// else if (className.equals("java.lang.String")) {
	// return IS_STRING;
	// } else if (className.equals("java.util.List")) {
	// return IS_LIST;
	// } else if (className.equals("java.lang.Long")) {
	// return IS_LONG;
	// } else if (className.startsWith("com.duowan.")) {
	// return IS_MODEL;
	// } else {
	// // this.valueClassType = IS_STRING;
	// throw new IllegalArgumentException("未知的值类型[" + className + "].");
	// }
	// }

}
