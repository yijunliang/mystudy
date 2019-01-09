package com.yjl.service.impl;

import org.springframework.stereotype.Component;

import com.yjl.entity.User;
import com.yjl.service.HelloService;
@Component
//服务降级处理类
public class HelloServiceImpl implements HelloService {
	
	@Override
	public String sayHello() {
		System.out.println("调用服务降级");
		return "error";
	}
	@Override
	public String sayHello1(String name) {
		return "error";
	}
	@Override
	public User sayHello2(String name, String password) {
		User user = new User();
		user.setUserName("未知");
		user.setPassword("未知");
		return user;
	}
	@Override
	public String sayHello3(User user) {
		return "error";
	}
}
