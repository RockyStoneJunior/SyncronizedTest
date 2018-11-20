package com.example.demo.dto;

import java.util.List;

public class UserLoginForm {
	
	private String type;
	
	private List<UserAccountForm> form;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<UserAccountForm> getForm() {
		return form;
	}

	public void setForm(List<UserAccountForm> form) {
		this.form = form;
	}
	
	public String toString() {
		String str = new String();
		
		str += "[type:" + type + "; form:";
		
		for(UserAccountForm account : form)
		{	
			str += account;
		}
		
		str += "]";
		
		return str;
	}
}
