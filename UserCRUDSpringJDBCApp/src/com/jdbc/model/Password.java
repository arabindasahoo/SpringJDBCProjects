package com.jdbc.model;

import javax.validation.constraints.NotEmpty;

public class Password {
	
	@NotEmpty
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
