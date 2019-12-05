package com.jdbc.dao.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.jdbc.dao.UserRowMapper;
import com.jdbc.model.User;
import com.jdbc.model.admin.Admin;

public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public boolean createAdmin(Admin admin) {
		
		String SQL = "insert into admin_details(name,email,phone,gender,maritalstatus,password,dob,doj) values(:name,:email,:phone,:gender,:maritalstatus,:password,:dob,:doj)";
		MapSqlParameterSource mapData = new MapSqlParameterSource();
		
		mapData.addValue("name", admin.getName());
		mapData.addValue("email", admin.getEmail());
		mapData.addValue("phone", admin.getPhone());
		mapData.addValue("gender", admin.getGender());
		mapData.addValue("maritalstatus", admin.getMarried());
		mapData.addValue("password", admin.getPassword());
		mapData.addValue("doj", admin.getDoj());
		mapData.addValue("dob", admin.getDob());
		
		int i = namedParameterJdbcTemplate.update(SQL, mapData);
		
		if(i > 0)
		{
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public List<Admin> updateAser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAdmin(String email, String password) {
		return null;
	}

	@Override
	public boolean checkAdmin(String email, String password) {
		
		String SQL = "SELECT * FROM admin_details WHERE email=:email and password=:password";
		
		MapSqlParameterSource mapData = new MapSqlParameterSource();
		mapData.addValue("email", email);
		mapData.addValue("password", password);
		
		List<Admin> admin =(List<Admin>) namedParameterJdbcTemplate.query(SQL, mapData, new AdminRowMapper());
		if(admin.size() > 0)
		{
		return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean checkIsExist() {
		
		String SQL = "select * from admin_details";
		
		List<Admin> user =(List<Admin>) namedParameterJdbcTemplate.query(SQL, new AdminRowMapper());
		if(user.size() > 0)
		{
			return true;
		}
		else
		{
		return false;
		}
	}

	@Override
	public boolean updatePassword(String password, String email) {
		
		String SQL = "UPDATE admin_details SET password=:password WHERE email=:email";
		MapSqlParameterSource mapdata = new MapSqlParameterSource();
		mapdata.addValue("password",password);
		mapdata.addValue("email", email);
		
		int i = namedParameterJdbcTemplate.update(SQL, mapdata);
		if(i > 0)
		{
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public List<User> getAllUsers() {
		
		String SQL = "SELECT * FROM user_details";
		
		List<User> user = namedParameterJdbcTemplate.query(SQL, new UserRowMapper());
		
		return user;
	}

	@Override
	public boolean approveUser(int id) {
		
		String SQL = "UPDATE user_details SET approval=1 WHERE id=:id";
		MapSqlParameterSource mapData = new MapSqlParameterSource();
		
		mapData.addValue("id", id);
		int i = namedParameterJdbcTemplate.update(SQL, mapData);
		if(i > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean updatePasswordCheck(String email, String phone) {
		
		String SQL = "SELECT * FROM admin_details WHERE email=:email and phone=:phone";
		MapSqlParameterSource mapData = new MapSqlParameterSource();
		mapData.addValue("email", email);
		mapData.addValue("phone", phone);
		
		List<Admin> admin = namedParameterJdbcTemplate.query(SQL, mapData, new AdminRowMapper());
		if(admin.size() > 0)
		{
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public boolean deleteUser(int id) {
		String SQL ="DELETE FROM user_details WHERE id=:id";
		MapSqlParameterSource mapData = new MapSqlParameterSource();
		mapData.addValue("id", id);
		int i = namedParameterJdbcTemplate.update(SQL, mapData);
		if(i > 0)
		{
			return true;
		}
		else 
		{
		return false;
		}
	}

}
