package com.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public boolean createUser(User user) {

		String SQL = "insert into user_details(name,email,phone,designation,gender,password,status,dob,approval) values(:uName,:uEmail,:uPhone,:uDesignation,:uGender,:uPassword,:uStatus,:uDob,:uApproval)";
		MapSqlParameterSource mapdata = new MapSqlParameterSource();
		mapdata.addValue("uName", user.getName());
		mapdata.addValue("uEmail", user.getEmail());
		mapdata.addValue("uPhone", user.getPhone());
		mapdata.addValue("uDesignation", user.getDesignation());
		mapdata.addValue("uGender", user.getGender());
		mapdata.addValue("uPassword", user.getPassword());
		mapdata.addValue("uStatus", user.getMarried());
		mapdata.addValue("uDob",user.getDob());
		mapdata.addValue("uApproval", 0);

		int i = namedParameterJdbcTemplate.update(SQL, mapdata);

		if (i > 0) {
			return true;
		}

		return false;
	}

	@Override
	public void deleteUser(int id) {

	}

	@Override
	public User getUserById(int id) {
		
			String SQL = "select * from user_details where id=:id";
		
		MapSqlParameterSource mapdata = new MapSqlParameterSource();
		mapdata.addValue("id",id);
		
		User user =namedParameterJdbcTemplate.queryForObject(SQL, mapdata, new UserRowMapper());
		 return user;
	}

	@Override
	public List<User> updateUser(User user) {
		return null;
	}

	@Override
	public boolean checkUser(String email, String password) {
		
		String SQL = "select * from user_details where email=:email and password=:password and approval=0";
		
		MapSqlParameterSource mapdata = new MapSqlParameterSource();
		mapdata.addValue("email", email);
		mapdata.addValue("password", password);
		
		List<User> user =namedParameterJdbcTemplate.query(SQL, mapdata, new UserRowMapper());
		if(user.size()>0)
		{
			return true;	
		}
		else {
		return false;
		}
	}

	@Override
	public List<User> getUser(String email,String password) {
		String SQL = "select * from user_details where email=:email and password=:password";
		
		MapSqlParameterSource mapdata = new MapSqlParameterSource();
		mapdata.addValue("email", email);
		mapdata.addValue("password", password);
		
		List<User> user =namedParameterJdbcTemplate.query(SQL, mapdata, new UserRowMapper());
		
		return user;
	}

	@Override
	public boolean checkIsExist(String email, String phone) {
	String SQL = "select * from user_details where email=:email or phone=:phone";
		
		MapSqlParameterSource mapdata = new MapSqlParameterSource();
		mapdata.addValue("email", email);
		mapdata.addValue("phone", phone);
		
		List<User> user =namedParameterJdbcTemplate.query(SQL, mapdata, new UserRowMapper());
		if(user.size() > 0)
		{
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean updatePassword(String password,String email) {
		
		String SQL = "UPDATE user_details SET password=:password WHERE email=:email";
		
		MapSqlParameterSource mapData =new MapSqlParameterSource();
		mapData.addValue("password", password);
		mapData.addValue("email", email);
		
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
