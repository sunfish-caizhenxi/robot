package cn.molasoftware.service.robot.base;

public interface BaseService<T>{
int deleteByPrimaryKey(Integer id);
	int insert(T record);
	
	int insertSelective(T record);
	
	T selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(T record);
	
	int updateByPrimaryKey(T record);
	
	

}
