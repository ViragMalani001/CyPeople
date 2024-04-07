package com.example.demo.entity;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {
	
	@Id
	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="username")
	private Login login;
	
	private String authority;
	
	
	public Authorities() {
		super();
	}
	public Authorities(String authority) {
		super();
		this.authority = authority;
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "Authorities [login=" + login + ", authority=" + authority + "]";
	}

	
	
	
}