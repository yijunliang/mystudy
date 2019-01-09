package com.yjl.mapper;

import java.util.List;
import java.util.Map;

import com.yjl.entity.Student;

/**
 * 定义执行sql方法的接口类
 * @author Administrator
 *
 */
public interface StudentMapper {
	
	public int insert(Student student);
	
	public int delete(int userId);
	
	public int update(Student student);
	
	public List<Student> findAll(String userName);
	
	public List<Student> findAllByLimit(Map<String, Object> param);
	
	public int findAllByLimitTotal(Map<String, Object> param);
	
	public Student findOne(int userId);
	
	public String getPassword(String userName);
	
	public String getRole(String userName);
}
