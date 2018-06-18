package cn.molasoftware.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.springframework.core.io.Resource;

public class SQLFileUtil {
	private static Map<String, Map<String, String>> cacheMap = new HashMap<String, Map<String, String>>();
	// XML解析器
	private static Unmarshaller unmarshaller = null;

	static {
		try {
			if (unmarshaller == null) {
				unmarshaller = JAXBContext.newInstance(SqlRecord.class).createUnmarshaller();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getSqlFromFile(String fileName, String sqlName) {
		Map<String, String> map = getSqlsFromFile(fileName);
		if (map == null) {
			return null;
		}
		return map.get(sqlName);
	}

	public static Map<String, String> getSqlsFromFile(String fileName) {
		Map<String, String> map = cacheMap.get(fileName);
		if (map != null) {
			return map;
		}
		map = new HashMap<String, String>();
		try {
			String locationPattern = "classpath*:/cn/nineox/**/" + fileName;
			Resource[] resources = PathUtil.getResourcesByPath(locationPattern);
			if (resources != null && resources.length > 0) {
				for (Resource resource : resources) {
					if(resource==null){
						continue;
					}
					map.putAll(getSqlsFromFile(resource.getInputStream()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, String> getSqlsFromFile(File file) {
		if(file==null || !file.exists()){
			return null;
		}
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			return getSqlsFromFile(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Map<String, String> getSqlsFromFile(InputStream is) {
		if(is==null){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			SqlRecord sqlRecord = (SqlRecord) unmarshaller.unmarshal(is);
			List<Sql> sqls = sqlRecord.sql;
			if (sqls != null && sqls.size() != 0) {
				for (Sql sql : sqls) {
					map.put(sql.name, sql.value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}

@XmlRootElement(name = "sqls")
@XmlAccessorType(XmlAccessType.FIELD)
class SqlRecord {
	List<Sql> sql;
}

class Sql {

	@XmlAttribute(name = "name")
	public String name;

	@XmlValue
	public String value;

}
