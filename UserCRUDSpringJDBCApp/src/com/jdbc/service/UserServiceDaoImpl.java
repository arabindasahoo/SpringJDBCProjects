package com.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbc.dao.UserDao;
import com.jdbc.model.User;
@Service("daoService")
public class UserServiceDaoImpl implements UserServiceDao{
	
	@Autowired
	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean createUser(User user) {
		
		return userDao.createUser(user);
		
	}

	@Override
	public void deleteUser(int id) {
		
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> updateUser(User user) {
		return null;
	}

	@Override
	public boolean checkUser(String email, String password) {
		
		return userDao.checkUser(email, password);
		
	}

	@Override
	public List<User> getUser(String email,String password) {
		return userDao.getUser(email,password);
	}

	@Override
	public boolean checkIsExist(String email, String phone) {
		return userDao.checkIsExist(email, phone);
	}

	@Override
	public boolean updatePassword(String password,String email) {
		return userDao.updatePassword(password,email);
	}

}
