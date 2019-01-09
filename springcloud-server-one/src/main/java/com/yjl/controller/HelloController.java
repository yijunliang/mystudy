package com.yjl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yjl.entity.User;

/**
 * hello work 控制器类
 * @author Administrator
 * @RestController和@Controller的区别
 * 1) 使用@RestController则Controller中的方法无法返回jsp页面，或者html，
 *    配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return里的内容。
   2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
               如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 *
 */
@RestController//标明此类是控制器类
@RequestMapping("/hello")//控制器请求路径
public class HelloController {
	
	@Autowired
	private DiscoveryClient discoverClient;
	
	@RequestMapping("/sayHello")//方法请求路径
	public String sayHello() {
		ServiceInstance localServiceInstance = discoverClient.getLocalServiceInstance();
		System.out.println("host:"+localServiceInstance.getHost()+"Id:" + localServiceInstance.getServiceId());
		return "Hello Work!!";
	}
	@RequestMapping(value="/sayHello1",method=RequestMethod.GET)//方法请求路径
	public String sayHello1(@RequestParam String name) {
		ServiceInstance localServiceInstance = discoverClient.getLocalServiceInstance();
		System.out.println("host:"+localServiceInstance.getHost()+"Id:" + localServiceInstance.getServiceId());
		return "Hello " + name;
	}
	@RequestMapping(value="/sayHello2",method=RequestMethod.GET)//方法请求路径
	public User sayHello2(@RequestHeader String name, @RequestHeader String password) {
		ServiceInstance localServiceInstance = discoverClient.getLocalServiceInstance();
		System.out.println("host:"+localServiceInstance.getHost()+"Id:" + localServiceInstance.getServiceId());
		User user = new User();
		user.setId(1);
		user.setPassword(password);
		user.setUserName(name);
		return user;
	}
	@RequestMapping(value="/sayHello3",method=RequestMethod.POST)//方法请求路径
	public String sayHello3(@RequestBody User user) {
		ServiceInstance localServiceInstance = discoverClient.getLocalServiceInstance();
		System.out.println("host:"+localServiceInstance.getHost()+"Id:" + localServiceInstance.getServiceId());
		return "Hello " + user.getUserName() + "," + user.getPassword();
	}
}
