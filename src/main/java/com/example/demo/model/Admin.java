package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="admin")
@Entity
public class Admin {
	
	@Id
	private int _id;
	@Column
	private String role;
	@Column
	private String name;
	@Column
	private String emailId;
	@Column
	private String password;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [_id=" + _id + ", role=" + role + ", name=" + name + ", emailId=" + emailId + ", password="
				+ password + "]";
	}
	
	
	
}
