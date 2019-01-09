package com.yjl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yjl.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/findStudent")
	@ResponseBody
	public Object findStudent(@RequestParam Map<String,Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return studentService.findStudent(paramMap);
	}
	
	@RequestMapping(value="deleteStudent", method=RequestMethod.GET)
	@ResponseBody
	public Object deleteStudent(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return studentService.deleteStudent(paramMap);
	}
	//网页是post请求-->zuul时还是post请求-->路由到此时是post请求
	@RequestMapping(value="addStudent", method=RequestMethod.GET)
	@ResponseBody
	public Object addStudent(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return studentService.addStudent(paramMap);
	}
	
	@RequestMapping(value="updateStudent", method=RequestMethod.GET)
	@ResponseBody
	public Object updateStudent(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return studentService.updateStudent(paramMap);
	}

}
