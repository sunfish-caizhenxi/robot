package cn.molasoftware.service.robot.student.impl;
import org.springframework.stereotype.Service;

import cn.molasoftware.dao.robot.pojo.Student;
import cn.molasoftware.service.robot.base.GetSqlSession;
import cn.molasoftware.service.robot.student.StudentService;
@Service
public class StudentServiceImpl extends GetSqlSession implements StudentService {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getSqlSession().delete("", id);
	}

	@Override
	public int insert(Student record) {
		return 0;
	}

	@Override
	public int insertSelective(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student selectByPrimaryKey(Integer id) {
		return getSqlSession().selectOne("cn.molasoftware.dao.robot.mapper.StudentMapper.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
