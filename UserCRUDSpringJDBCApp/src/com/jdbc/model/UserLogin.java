package com.jdbc.model;

import javax.validation.constraints.NotEmpty;

public class UserLogin {

	
	@NotEmpty(message="Field should not be blank")
	private String email;
	@NotEmpty(message="Field should not be blank")
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
