package com.example.demo.entity;

import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="leave_request")
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String employeeId;
	private Date startDate; 
	private Date endDate;
	
	
	@Size(max=500)
	private String reason;
	
	private String approvalStatus;
	
	public LeaveRequest() {
		super();
	}
	
	
	public LeaveRequest(int id, String name, String employeeId, Date startDate, Date endDate, String reason, String approvalStatus) {
		super();
		this.id = id;
		this.name = name;
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.approvalStatus = approvalStatus;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	public String getApprovalStatus() {
		return approvalStatus;
	}


	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}


	@Override
	public String toString() {
		return "EmployeeLeave [id=" + id + ", name=" + name + ", employeeId=" + employeeId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", reason=" + reason + ", approvalStatus=" + approvalStatus + "]";
	}


	
	
	
	

}
