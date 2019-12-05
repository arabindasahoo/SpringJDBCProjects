package com.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jdbc.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setDesignation(rs.getString("designation"));
		user.setGender(rs.getString("gender"));
		user.setMarried(rs.getString("status"));
		user.setDob(rs.getDate("dob"));
		user.setApproval(rs.getInt("approval"));
		return user;
	}

}
