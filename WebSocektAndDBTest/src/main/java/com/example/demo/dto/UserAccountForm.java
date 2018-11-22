package com.example.demo.dto;

public class UserAccountForm {

	private String username;
	
	private String password;
	
	private String token;
	
	private String branch;

	private Long branch_id;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(Long branch_id) {
		this.branch_id = branch_id;
	}
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String toString() {
		
		return "[username:" + username + "; password:" + password + "; token:" + token + "; branch:" + branch + "; branch_id:" + branch_id + "]";
	}
}
