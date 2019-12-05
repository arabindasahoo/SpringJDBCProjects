package com.jdbc.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jdbc.dao.admin.AdminDao;
import com.jdbc.model.User;
import com.jdbc.model.admin.Admin;

public class AdminDaoServiceImpl implements AdminDaoService {
	
	@Autowired
	AdminDao adminDao;
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean createAdmin(Admin admin) {
		return adminDao.createAdmin(admin);
	}

	@Override
	public List<Admin> updateAser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkAdmin(String email, String password) {
		return adminDao.checkAdmin(email, password);
	}

	@Override
	public boolean checkIsExist() {
		
		return adminDao.checkIsExist();
		
	}

	@Override
	public boolean updatePassword(String password, String email) {
		return adminDao.updatePassword(password, email);
	}

	@Override
	public List<User> getAllUsers() {
		return adminDao.getAllUsers();
		
	}

	@Override
	public boolean approveUser(int id) {
		
		return adminDao.approveUser(id);
	}

	@Override
	public boolean updatePasswordCheck(String email, String phone) {
		return adminDao.updatePasswordCheck(email, phone);
	}

	@Override
	public boolean deleteUser(int id) {
		return adminDao.deleteUser(id);
	}

}
