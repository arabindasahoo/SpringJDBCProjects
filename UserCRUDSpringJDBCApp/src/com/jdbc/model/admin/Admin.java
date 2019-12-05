package com.jdbc.model.admin;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

public class Admin {
	
	private int id;

	@NotEmpty(message = "Field Should not be Blank")
	private String name;

	@NotEmpty(message = "Field Should not be Blank")
	@Email(message="Enter a Valid Email")
	private String email;
	@NotEmpty(message = "Field Should not be Blank")
	private String phone;
	@NotEmpty(message = "Field Should not be Blank")
	private String password;
	@NotEmpty(message = "Field Should not be Blank")
	private String married;
	@NotEmpty(message = "Select One Gender")
	private String gender;
	
	@DateTimeFormat(pattern="dd-MM-yy")
	private Date dob;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date doj;

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
	


}
