package com.yjl.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yjl.service.impl.UserServiceImpl;

@FeignClient(name = "hello-service", fallback = UserServiceImpl.class)
public interface UserService {
	@RequestMapping("user/findAllByLimit")
	/**
	 * 查询用户数据
	 * @param paramMap
	 * @return
	 */
	public String queryUser(@RequestParam Map<String,Object> paramMap);
	
	@RequestMapping("user/springbootDeleteUser")
	/**
	 * 删除用户数据
	 * @param paramMap
	 * @return
	 */
	public String deleteUser(@RequestParam Map<String,Object> paramMap);
	
	@RequestMapping("user/springbootAddUser")
	/**
	 * 添加用户数据
	 * @param paramMap
	 * @return
	 * 
	 */
	public String addUser(@RequestParam Map<String,Object> paramMap);
	
	@RequestMapping("user/sprinbootUpdateUser")
	/**
	 * 更新用户数据
	 * @param paramMap
	 * @return
	 */
	public String updateUser(@RequestParam Map<String,Object> paramMap);
	

}
