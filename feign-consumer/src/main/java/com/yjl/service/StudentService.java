package com.yjl.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yjl.service.impl.StudentServiceImpl;

/**
 * 注册学生服务实例
 * @author Administrator
 *
 */
@FeignClient(value = "student-service", fallback = StudentServiceImpl.class)
public interface StudentService {
	@RequestMapping(value="student/findStudentByLimit",method=RequestMethod.GET)
	public String findStudent(@RequestParam Map<String,Object> paramMap);
	
	@RequestMapping(value="student/addStudent",method=RequestMethod.GET)
	public String addStudent(@RequestParam Map<String,Object> paramMap);
	
	@RequestMapping(value="student/deleteStudent",method=RequestMethod.GET)
	public String deleteStudent(@RequestParam Map<String,Object> paramMap);
	
	@RequestMapping(value="student/updateStudent",method=RequestMethod.GET)
	public String updateStudent(@RequestParam Map<String,Object> paramMap);

}
