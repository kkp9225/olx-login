package com.olxlogin.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "This Model holds information about individual user")
public class User {
	
	int id;
	@ApiModelProperty(value = "First name of user")
	String firstname;
	@ApiModelProperty(value = "Last name of user")
	String lastname;
	@ApiModelProperty(value = "Username of user")
	String username;
	@ApiModelProperty(value = "Email of user")
	String email;
	@ApiModelProperty(value = "Phone.No of user")
	String phone;
	@ApiModelProperty(value = "Password")
	String password;
	String role;
	
	public User() {}
	
	public User(int id, String firstname, String lastname, String username, String email, String phone,
			String password, String role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", email=" + email + ", phone=" + phone + ", password=" + password + "]";
	}
	
}
