package com.yjl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yjl.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUser")
	@ResponseBody
	public Object queryUser(@RequestParam Map<String,Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return userService.queryUser(paramMap);
	}
	
	@RequestMapping(value="deleteUser", method=RequestMethod.GET)
	@ResponseBody
	public Object deleteUser(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return userService.deleteUser(paramMap);
	}
	//网页是post请求-->zuul时还是post请求-->路由到此时是post请求
	@RequestMapping(value="addUser", method=RequestMethod.GET)
	@ResponseBody
	public Object addUser(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return userService.addUser(paramMap);//调用服务提供者服务时使用的get请求
	}
	
	@RequestMapping(value="updateUser", method=RequestMethod.GET)
	@ResponseBody
	public Object updateUser(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		return userService.updateUser(paramMap);
	}

}
