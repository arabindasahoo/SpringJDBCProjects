package com.jdbc.service;

import java.util.List;

import com.jdbc.model.User;

public interface UserServiceDao {
	
	abstract public boolean createUser(User user);
	abstract public void deleteUser(int id);
	abstract public User getUserById(int id);
	abstract public List<User> updateUser(User user);
	abstract public List<User> getUser(String email,String password);
	abstract public boolean checkUser(String email, String password);
	
	abstract public boolean checkIsExist(String email, String phone);
	
	abstract public boolean updatePassword(String password,String email);
}
