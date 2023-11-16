package com.busticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
	
	@Id
	private String username;
	private String password;
	private String name;
	private String mobilenumber;
	private String emailid;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public Admin(String username, String password, String name, String mobilenumber, String emailid) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.mobilenumber = mobilenumber;
		this.emailid = emailid;
	}
	public Admin() {
		super();
	}
	
	
	

}
