package cn.molasoftware.service.robot.base;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
public class GetSqlSession {
	@Resource(name="sqlSessionFactory") 
	private  SqlSessionFactory sqlSessionFactory;
	public SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}

}
