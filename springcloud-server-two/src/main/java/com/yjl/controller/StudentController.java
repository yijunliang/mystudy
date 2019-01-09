package com.yjl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.utils.ResultUtil;
import com.yjl.dao.StudentDao;
import com.yjl.entity.Student;

/**
 * 学生controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("/findStudentByLimit")
	@ResponseBody
	public Object findStudentByLimit(@RequestParam Map<String, Object> paramMap) {
		System.out.println(paramMap);
		List<Student> list = studentDao.findStudentByLimit(paramMap);
		int total = studentDao.findStudentByLimitTotal(paramMap);
		return ResultUtil.wrapupResult(list,total);
	}
	@RequestMapping("/addStudent")
	@ResponseBody
	public Object addStudent(@RequestParam Map<String, Object> paramMap) {
		System.out.println(paramMap);
		boolean addStudent = studentDao.addStudent(paramMap);
		return addStudent ? "success" : "fail";
	}
	
	@RequestMapping(value="/updateStudent", method=RequestMethod.GET)
	@ResponseBody
	public Object updateStudent(@RequestParam Map<String, Object> paramMap) {
		System.out.println(paramMap);
		boolean updateStudent = studentDao.updateStudent(paramMap);
		return updateStudent ? "success" : "fail";
	}
	
	@RequestMapping(value="/deleteStudent", method=RequestMethod.GET)
	@ResponseBody
	public Object deleteStudent(@RequestParam Map<String, Object> paramMap) {
		System.out.println(paramMap);
		boolean deleteStudent = studentDao.deleteStudent(paramMap);
		return deleteStudent ? "success" : "fail";
	}

}
