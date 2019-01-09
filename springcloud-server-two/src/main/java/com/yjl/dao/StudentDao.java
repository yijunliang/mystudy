package com.yjl.dao;

import java.util.List;
import java.util.Map;

import com.yjl.entity.Student;

/**
 * 学生增删改查接口
 * @author Administrator
 *
 */
public interface StudentDao {
	/*
	 * 分页查询学生信息
	 */
	public List<Student> findStudentByLimit(Map<String, Object> pramMap);
	/**
	 * 增加学生信息
	 * @param pramMap
	 * @return
	 */
	public boolean addStudent(Map<String, Object> pramMap);
	/**
	 * 更新学生信息
	 * @param pramMap
	 * @return
	 */
	public boolean updateStudent(Map<String, Object> pramMap);
	
	/**
	 * 删除学生信息
	 * @param pramMap
	 * @return
	 */
	public boolean deleteStudent(Map<String, Object> pramMap);
	/**
	 * 查询总数
	 * @return
	 */
	public int findStudentByLimitTotal(Map<String, Object> pramMap);

}
