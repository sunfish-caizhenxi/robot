package cn.molasoftware.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class CollectionUtil {

	/**
	 * 把一个list转换成一个Map，以list中每个对象的property属性的值为Key，对象自身为value。
	 * <br>适用场景：list中对象的property值具有唯一性。
	 * @param list
	 * @param property
	 * @return 传入无效的参数时，返回一个空的Map实例。
	 */
	
	public static <T> LinkedHashMap<Object,T> listToMapByProperty(List<T> list, String property) {
		LinkedHashMap<Object,T> map = new LinkedHashMap<Object,T>();
		if (list == null || list.isEmpty() || StrUtil.isBlank(property)) {
			return map;
		}
		for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
			T object = iter.next();
			Object key = null;
			try {
				key = ObjectUtil.getBeanProperty(object, property);
				map.put(key, object);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("获取对象属性时产生异常:" + e);
			}

		}
		return map;
	}
	
	/**
	 * 把一个List根据其中对象的属性值进行分组，返回一个Map，属性值为key，分组（list）对象为value。
	 * @param list
	 * @param property
	 * @return
	 */
	
	public static <T> LinkedHashMap<Object,List<T>> listToGroupByProperty(List<T> list, String property){
        LinkedHashMap<Object,List<T>> map = new LinkedHashMap<Object,List<T>>();
        if(list == null || list.isEmpty() || StrUtil.isBlank(property))
            return map;
        for(Iterator<T> iter = list.iterator(); iter.hasNext();){
            T object = iter.next();
            Object key = null;
            try{
                key = ObjectUtil.getBeanProperty(object, property);
                List<T> keyList = null;
                if(map.containsKey(key)){
                	keyList = (List<T>)map.get(key);
                	keyList.add(object);
                }else{
                	keyList = new ArrayList<T>();
                	keyList.add(object);
                	map.put(key, keyList);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return map;
    }	
}
