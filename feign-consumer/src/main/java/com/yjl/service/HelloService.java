package com.yjl.service;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yjl.entity.User;
import com.yjl.service.impl.HelloServiceImpl;
//@FeignClient(name = "hello-service", configuration = DisableHystrixConfiguration.class)//和服务提供者hello-service绑定
@FeignClient(value = "hello-service", fallback = HelloServiceImpl.class)//和服务提供者hello-service绑定
public interface HelloService {
	@RequestMapping("hello/sayHello")//和提供者具体类及方法绑定
	public String sayHello();
	
	@RequestMapping(value="hello/sayHello1",method=RequestMethod.GET)//和提供者具体类及方法绑定
	public String sayHello1(@RequestParam("name") String name);
	
	@RequestMapping(value="hello/sayHello2",method=RequestMethod.GET)//和提供者具体类及方法绑定
	public User sayHello2(@RequestHeader("name") String name, @RequestHeader("password") String password);
	
	@RequestMapping(value="hello/sayHello3",method=RequestMethod.POST)//和提供者具体类及方法绑定
	public String sayHello3(@RequestBody User user);
	
}
