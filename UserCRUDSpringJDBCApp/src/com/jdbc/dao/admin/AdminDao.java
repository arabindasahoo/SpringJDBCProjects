package com.jdbc.dao.admin;

import java.util.List;

import com.jdbc.model.User;
import com.jdbc.model.admin.Admin;

public interface AdminDao {
	
	abstract public boolean createAdmin(Admin admin);

	abstract public List<Admin> updateAser(User user);
	abstract public List<Admin> getAdmin(String email,String password);
	abstract public boolean checkAdmin(String email, String password);
	
	abstract public boolean checkIsExist();
	
	abstract public boolean updatePasswordCheck(String email,String phone);
	abstract public boolean updatePassword(String password,String email);
	
	abstract public List<User> getAllUsers();
	
	abstract public boolean approveUser(int id);
	
	abstract public boolean deleteUser(int id);

}
