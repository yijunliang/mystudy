package com.yjl.mapper;

import java.util.List;
import java.util.Map;

import com.yjl.entity.User;

/**
 * 定义执行sql方法的接口类
 * @author Administrator
 *
 */
public interface UserMapper {
	
	public void insert(User user);
	
	public void delete(int userId);
	
	public void update(User user);
	
	public List<User> findAll(String userName);
	
	public List<User> findAllByLimit(Map<String, Object> param);
	
	public int findAllByLimitTotal(Map<String, Object> param);
	
	public User findOne(int userId);
	
	public String getPassword(String userName);
	
	public String getRole(String userName);
}
