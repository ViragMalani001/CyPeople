package com.example.demo.Enum;

public enum EndPointEnum {
//	DASHBOARD
	DASHBOARD("/dashboard"),
	
//	EMPLOYEES
	EMPLOYEES("/employees"),
	
//	CLIENTS
	CLIENTS("/clients"),
	
//	PROJECTS
	PROJECTS("/projects"),
	
//	PAYROLL
	PAYROLL("/payroll"),
	
//	REPORTS
	REPORTS("/reports"),
	
//	ACCESSDENIED
	ACCESSDENIED("/access-denied-page");
	
	private final String endPoint;

	private EndPointEnum(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}
	
	
}
