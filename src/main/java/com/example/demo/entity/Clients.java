package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clients")
public class Clients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String clientName;
	private String companyName;
	private String clientId;
	
	@Email
	private String email;
	
	@NotNull
	@Size(min=10, max=10, message = "Enter Valid Number")
	private String mobileNo;
	
	public Clients() {
		super();
	}
	
	public Clients(int id, String clientName, String companyName, String clientId, String email,
			@NotNull @Size(min = 10, max = 10, message = "Enter Valid Number") String mobileNo) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.companyName = companyName;
		this.clientId = clientId;
		this.email = email;
		this.mobileNo = mobileNo;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Override
	public String toString() {
		return "Clients [id=" + id + ", clientName=" + clientName + ", companyName=" + companyName + ", clientId=" + clientId
				+ ", email=" + email + ", mobileNo=" + mobileNo + "]";
	}

	
	
	
}

