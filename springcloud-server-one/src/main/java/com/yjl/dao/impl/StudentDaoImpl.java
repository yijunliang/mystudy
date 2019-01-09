package com.yjl.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.utils.ParamUtil;
import com.yjl.dao.StudentDao;
import com.yjl.entity.Student;
import com.yjl.mapper.StudentMapper;
@Service
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> findStudentByLimit(Map<String, Object> paramMap) {
		Map<String, Object> queryParamMap = ParamUtil.wrapupParamForPaging(paramMap);
		return studentMapper.findAllByLimit(queryParamMap);
	}

	@Override
	public boolean addStudent(Map<String, Object> pramMap) {
		Student student = new Student();
		student.setStudentName(ParamUtil.getStringParamByName(pramMap, "studentName", ""));
		student.setStudentSex(ParamUtil.getIntegerParamByName(pramMap, "studentSex", 0));
		student.setStudentSchool(ParamUtil.getStringParamByName(pramMap, "studentSchool",""));
		student.setStudentClass(ParamUtil.getStringParamByName(pramMap, "studentClass",""));
		student.setStudentNo(ParamUtil.getStringParamByName(pramMap, "studentNo", ""));
		int insert = studentMapper.insert(student);
		return insert > 0 ? true : false;
	}

	@Override
	public boolean updateStudent(Map<String, Object> pramMap) {
		Student student = new Student();
		student.setId(ParamUtil.getIntegerParamByName(pramMap, "id"));
		student.setStudentName(ParamUtil.getStringParamByName(pramMap, "studentName", ""));
		student.setStudentSex(ParamUtil.getIntegerParamByName(pramMap, "studentSex", 0));
		student.setStudentSchool(ParamUtil.getStringParamByName(pramMap, "studentSchool",""));
		student.setStudentClass(ParamUtil.getStringParamByName(pramMap, "studentClass",""));
		student.setStudentNo(ParamUtil.getStringParamByName(pramMap, "studentNo", ""));
		int update = studentMapper.update(student);
		return update > 0 ? true : false;
	}

	@Override
	public boolean deleteStudent(Map<String, Object> pramMap) {
		int delete = 0;
		String[] deleteUserIdArray = ParamUtil.stringToArray(pramMap, "deleteUserId", ",");
		for(int i = 0; i < deleteUserIdArray.length; i++) {
			delete += studentMapper.delete(Integer.parseInt(deleteUserIdArray[i]));
		}
		return delete > 0 ? true : false;
	}

	@Override
	public int findStudentByLimitTotal(Map<String, Object> pramMap) {
		return studentMapper.findAllByLimitTotal(pramMap);
	}

}
