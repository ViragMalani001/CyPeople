package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="projects")
public class Projects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String projectName;
	private String clientName;
	private String priority;
	private String type;
	private String rate;
	private String teamLeader;
	private Date startDate;
	private Date endDate;
	private String status;
	private String projectDetail;
	public Projects() {
		super();
	}
	public Projects(int id, String projectName, String clientName, String priority, String type, String rate,
			String teamLeader, Date startDate, Date endDate, String status, String projectDetail) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.clientName = clientName;
		this.priority = priority;
		this.type = type;
		this.rate = rate;
		this.teamLeader = teamLeader;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectDetail = projectDetail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProjectDetail() {
		return projectDetail;
	}
	public void setProjectDetail(String projectDetail) {
		this.projectDetail = projectDetail;
	}
	@Override
	public String toString() {
		return "Projects [id=" + id + ", projectName=" + projectName + ", clientName=" + clientName + ", priority="
				+ priority + ", type=" + type + ", rate=" + rate + ", teamLeader=" + teamLeader + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + ", projectDetail=" + projectDetail
				+ "]";
	}
	
	
	
	
	
	
}
