package com.yjl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.utils.ParamUtil;
import com.yjl.dao.UserDao;
import com.yjl.entity.User;
import com.yjl.mapper.UserMapper;
/**
 * 用户接口实现类
 * @author Administrator
 *
 */
@Service
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public void deleteUser(int userId) {
		userMapper.delete(userId);
	}

	@Override
	public void updateUser(User user) {
		userMapper.update(user);
	}

	@Override
	public List<User> findUser(String userName) {
		return userMapper.findAll(userName);
	}

	@Override
	public User findOneUser(int userId) {
		return userMapper.findOne(userId);
	}

	@Override
	public List<User> findUserByLimit(Map<String, Object> paramMap) {
		Map<String, Object> queryParamMap = ParamUtil.wrapupParamForPaging(paramMap);
		return userMapper.findAllByLimit(queryParamMap);
	}

	@Override
	public int findUserByLimitTotal(Map<String, Object> param) {
		return userMapper.findAllByLimitTotal(param);
	}

	@Override
	public List<User> findUserByIds(List<Integer> ids) {
		List<User> userList = new ArrayList<User>();
		for(int id : ids) {
			User user = userMapper.findOne(id);
			userList.add(user);
		}
		return userList;
	}

}
