package com.example.demo.entity;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String employeeId;

	@NotNull
	@Size(min=10, max=10, message = "Enter Valid Number")
	private String phone;
	
	private String gender;
	
	private Date joinDate;
	private String role;
	
	@Column(name="salary_hourly_ret")
	private String salaryHourlyRet;
	
	@Column(name = "house_rent_allowances")
	private String houseRentAllowances;
	
	@Column(name = "medical_allowances")
	private String medicalAllowances;
	
	@Column(name = "special_allowances")
	private String specialAllowances;
	
	@Column(name= "health_insurance")
	private String healthInsurance;
	
	@Column(name = "tds")
	private String TDS;
	
	
	@Email
	private String email;
	
	@Column
	private String performance;
	
	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = Employee.class)
//	@JoinColumn(name = "name")
//	private Login login;
//	public Login getLogin() {
//		return login;
//	}
//
//	public void setLogin(Login login) {
//		this.login = login;		
//	}
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, String employeeId,
			@NotNull @Size(min = 10, max = 10, message = "Enter Valid Number") String phone, Date joinDate, String role,
			String salaryHourlyRet, String houseRentAllowances, String medicalAllowances, String specialAllowances,
			String healthInsurance, String tDS, @Email String email, String gender, String performance) {
		super();
		this.id = id;
		this.name = name;
		this.employeeId = employeeId;
		this.phone = phone;
		this.joinDate = joinDate;
		this.role = role;
		this.salaryHourlyRet = salaryHourlyRet;
		this.houseRentAllowances = houseRentAllowances;
		this.medicalAllowances = medicalAllowances;
		this.specialAllowances = specialAllowances;
		this.healthInsurance = healthInsurance;
		TDS = tDS;
		this.email = email;
		this.gender = gender;
		this.performance = performance;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSalaryHourlyRet() {
		return salaryHourlyRet;
	}

	public void setSalaryHourlyRet(String salaryHourlyRet) {
		this.salaryHourlyRet = salaryHourlyRet;
	}

	public String getHouseRentAllowances() {
		return houseRentAllowances;
	}

	public void setHouseRentAllowances(String houseRentAllowances) {
		this.houseRentAllowances = houseRentAllowances;
	}

	public String getMedicalAllowances() {
		return medicalAllowances;
	}

	public void setMedicalAllowances(String medicalAllowances) {
		this.medicalAllowances = medicalAllowances;
	}

	public String getSpecialAllowances() {
		return specialAllowances;
	}

	public void setSpecialAllowances(String specialAllowances) {
		this.specialAllowances = specialAllowances;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getTDS() {
		return TDS;
	}

	public void setTDS(String tDS) {
		TDS = tDS;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", employeeId=" + employeeId + ", phone=" + phone + ", gender="
				+ gender + ", joinDate=" + joinDate + ", role=" + role + ", salaryHourlyRet=" + salaryHourlyRet
				+ ", houseRentAllowances=" + houseRentAllowances + ", medicalAllowances=" + medicalAllowances
				+ ", specialAllowances=" + specialAllowances + ", healthInsurance=" + healthInsurance + ", TDS=" + TDS
				+ ", email=" + email + ", performance=" + performance + "]";
	}


}
