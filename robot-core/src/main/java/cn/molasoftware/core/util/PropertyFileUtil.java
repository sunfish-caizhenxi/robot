/**
 * 
 */
package cn.molasoftware.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author caizx
 *
 */
public class PropertyFileUtil {
	
	public static Map<String,String> load(File f) throws Exception {
		if (f == null || !f.exists()){
			return null;
		}
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(f);
			return load(fis);
		} finally {
			fis.close();
		}
	}

	public static Map<String,String> load(InputStream is) throws Exception {
		Properties props = new Properties();
		props.load(is);
		return load(props);
	}
	
	public static Map<String,String> load(Properties props) throws Exception {
		if(props==null||props.keySet().isEmpty()){
			return null;
		}
		Map<String,String> values = new HashMap<String,String>();
		for(Object key : props.keySet()){
			values.put(key.toString(), props.getProperty(key.toString()));
		}
		return values;

	}
	
	public static void persit(File f, Map<String,String> values) throws Exception {
		if (f == null||values==null||values.isEmpty()){
			return;
		}
		FileOutputStream os = null;

		try {
			os = new FileOutputStream(f);
			persit(os,values);
		} finally {
			os.close();
		}
	}
	
	public static void persit(OutputStream os, Map<String,String> values) throws Exception {
		if (os == null||values==null||values.isEmpty()){
			return;
		}
		Properties props = new Properties();
		persit(props,values);
		try {
			props.store(os, null);
		} catch (Exception e) {
			throw e;
		}

	}

	public static void persit(Properties props, Map<String,String> values) throws Exception {
		if (props == null||values==null||values.isEmpty()){
			return;
		}
		for(String key : values.keySet()){
			setProperty(props, key.toString(), values.get(key.toString()));
		}
	}
	
	public static void setProperty(Properties props, String key, String value) {
		if (value == null){
			return;
		}
		props.setProperty(key, value);
	}
}
