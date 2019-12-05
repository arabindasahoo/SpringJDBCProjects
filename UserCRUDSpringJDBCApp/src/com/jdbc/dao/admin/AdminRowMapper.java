package com.jdbc.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jdbc.model.admin.Admin;

public class AdminRowMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs,int rowNum) throws SQLException {
			
		Admin admin = new Admin();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setEmail(rs.getString("email"));
		admin.setPhone(rs.getString("phone"));
		admin.setPassword(rs.getString("password"));
		admin.setMarried(rs.getString("maritalstatus"));
		admin.setGender(rs.getString("gender"));
		admin.setDob(rs.getDate("dob"));
		admin.setDoj(rs.getDate("doj"));
		
		return admin;
	}

}
