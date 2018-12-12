
package cn.molasoftware.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author caizx
 *
 */
public class ObjectUtil {
	
	/**
	 * 判断一个对象是否为null
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	/**
	 * 判断一个对象是否为非null
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}
	
	/**
	 * 复制对象的属性
	 * @param srcObject 源对象
	 * @param desObject 目标对象
	 */
	public static void copyBeanProperties(Object srcObject, Object desObject) {
		try {
			PropertyUtils.copyProperties(desObject, srcObject);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取对象里的属性值
	 * @param bean
	 * @param name
	 * @return
	 */
	public static Object getBeanProperty(Object bean, String name) {
		try {
			return PropertyUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 设置对象里的属性值
	 * @param bean
	 * @param name
	 * @return
	 */
	public static void setBeanProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 为对象里的为null的属性设置默认值
	 * @param bean
	 * @param name
	 * @return
	 */
	public static void setBeanProperty4OnlyNull(Object bean) {
		if(bean == null){
			return;
		}
		// 取得目标对象的属性列表
		PropertyDescriptor[] desObjectFields = PropertyUtils.getPropertyDescriptors(bean);
		try {
			for (PropertyDescriptor desObjectField : desObjectFields) {
				if(desObjectField.getWriteMethod() == null){
					continue;
				}
				//获取属性名字
				String name = desObjectField.getName();
				//获取属性值,使用desObjectField.getValue()不知道为什么都为null，现改为直接调用方法取值
				Object value = getBeanProperty(bean, name);
				if(value == null){
					if(Integer.class.isAssignableFrom(desObjectField.getPropertyType())){
						setBeanProperty(bean, name, new Integer("0"));
					}else if(Short.class.isAssignableFrom(desObjectField.getPropertyType())){
						setBeanProperty(bean, name, new Short("0"));
					}else if(Long.class.isAssignableFrom(desObjectField.getPropertyType())){
						setBeanProperty(bean, name, new Long("0"));
					}else if(Double.class.isAssignableFrom(desObjectField.getPropertyType())){
						setBeanProperty(bean, name, new Double(0.0));
					}else if(Boolean.class.isAssignableFrom(desObjectField.getPropertyType())){
						setBeanProperty(bean, name, false);
					}else if(String.class.isAssignableFrom(desObjectField.getPropertyType())){
						setBeanProperty(bean, name, "");
					}
				}								
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复制属性，只有当目标对象desObject里的属性值为null的时候，才会把源对象srcObject里的值复制到目标对象上
	 * @param srcObject 源对象
	 * @param desObject 目标对象
	 */
	public static Object copyBeanProperties4OnlyNull(Object srcObject, Object desObject){
		if(srcObject == null || desObject == null){
			return null;
		}
		// 取得目标对象的属性列表
		PropertyDescriptor[] desObjectFields = PropertyUtils.getPropertyDescriptors(desObject);
		try {
			for (PropertyDescriptor desObjectField : desObjectFields) {
				if(desObjectField.getWriteMethod() == null){
					continue;
				}
				//获取属性名字
				String name = desObjectField.getName();
				//获取属性值,使用desObjectField.getValue()不知道为什么都为null，现改为直接调用方法取值
				Object value = getBeanProperty(desObject, name);
				//由于部分属性值为空不为null，所以判断object同时判断是否空值
				if(value == null && srcObject != null){
					Object newValue = getBeanProperty(srcObject,name);
					setBeanProperty(desObject, name, newValue);
				}				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return desObject;
	}
	
}