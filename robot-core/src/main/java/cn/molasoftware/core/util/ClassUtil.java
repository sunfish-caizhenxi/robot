package cn.molasoftware.core.util;

public class ClassUtil {
	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("加载类出错！");
		}
	}

	public static boolean exist(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
