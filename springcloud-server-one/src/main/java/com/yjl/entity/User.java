package com.yjl.entity;
/**
 * 学生实体类
 * @author Administrator
 *
 */
public class User {
	
	private int id;
	
	private String userName;
	
	private String password;
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}