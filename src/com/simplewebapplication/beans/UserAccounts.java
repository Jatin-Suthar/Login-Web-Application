package com.simplewebapplication.beans;

public class UserAccounts {
	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";
	
	private String username;
	private String gender;
	private String password;
	
	public UserAccounts() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
