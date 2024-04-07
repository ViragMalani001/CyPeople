package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	
	@Id
	@Column(name = "username")
	private String username;
	
	@OneToOne(mappedBy = "login", cascade =CascadeType.ALL)
	private Authorities authorities;

	public Authorities getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Authorities authorities) {
		this.authorities = authorities;
	}
	
//	@OneToMany(mappedBy = "login", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} ,targetEntity = Login.class)
//	private Employee employee;
//	
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private byte enabled;

	public Login() {
		super();
	}

public Login(String username, String password, byte enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public byte getEnabled() {
	return enabled;
}

public void setEnabled(byte enabled) {
	this.enabled = enabled;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

@Override
public String toString() {
	return "Login [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
}





	

	
	
	
	
}
