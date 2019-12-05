package com.jdbc.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class ForgotPassword {
	
	@NotEmpty
	@Email
	private String Email;
	
	@NotEmpty
	private String phone;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	
	

}
