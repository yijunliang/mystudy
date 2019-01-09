package com.yjl.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yjl.entity.User;
import com.yjl.service.HelloService;

import ch.qos.logback.core.net.SyslogOutputStream;
@RequestMapping("hello")
@RestController
public class HelloController {
	@Autowired
	private HelloService helloService;
	@RequestMapping("/sayHello")
	@ResponseBody
	public String sayHello() {
		System.out.println("服务消费者控制器类hello");
		return helloService.sayHello();
	}
	
	@RequestMapping("/sayHello1")
	@ResponseBody
	public String sayHello1() {
		String sayHello1 = helloService.sayHello1("易俊良");
		User sayHello2 = helloService.sayHello2("yijunlaing", "123456");
		User user = new User();
		user.setPassword("66666666");
		user.setUserName("黄彩珍");
		String sayHello3 = helloService.sayHello3(user );
		StringBuilder sb = new StringBuilder();
		sb.append(sayHello1 + "\n");
		sb.append("名字:" +sayHello2.getUserName() + "密码:" + sayHello2.getPassword() + "\n");
		sb.append(sayHello3 + "\n");
		return sb.toString();
	}
}
