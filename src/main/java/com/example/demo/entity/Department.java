	package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String departmentName;
	private String departmentHead;
	
	@Column(name="total_employee")
	private String totalEmployee;
	
	public Department() {
		super();
	}

	public Department(int id, String departmentName, String departmentHead, String totalEmployee) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.departmentHead = departmentHead;
		this.totalEmployee = totalEmployee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentHead() {
		return departmentHead;
	}
	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}

	public String getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(String totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	@Override
	public String toString() {
		return "EmployeeDepartment [id=" + id + ", departmentName=" + departmentName + ", departmentHead="
				+ departmentHead + ", totalEmployee=" + totalEmployee;
	}
	
	
	
	
	
}
