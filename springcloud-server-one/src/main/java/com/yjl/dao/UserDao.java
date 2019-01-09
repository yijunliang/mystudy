package com.yjl.dao;

import java.util.List;
import java.util.Map;

import com.yjl.entity.User;

/**
 * 定义对用户增删改查方法的接口
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 增加用户
	 * @param user
	 */
	public void insertUser(User user);
	/**
	 * 根据用户ID删除用户
	 * @param userId
	 */
	public void deleteUser(int userId);
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
	/**
	 * 根据用户名称查找用户(支持模糊查询)
	 * @param userName
	 * @return
	 */
	public List<User> findUser(String userName);
	
	public List<User> findUserByLimit(Map<String, Object> param);
	
	public int findUserByLimitTotal(Map<String, Object> param);
	
	public User findOneUser(int userId);
	
	public List<User> findUserByIds(List<Integer> ids);

}
