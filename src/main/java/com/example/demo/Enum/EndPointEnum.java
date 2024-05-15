package com.example.demo.Enum;

public enum EndPointEnum {
//	DASHBOARD
	DASHBOARD("/dashboard/dashboard"),
	DASHBOARDTODO("/dashboard"),
	
//	EMPLOYEE
	EMPLOYEE("/employees/employees"),
	EMPLOYEELIST("/employees/emp-list"),
	EMPLOYEEADD("/employees/emp-add"),
	EMPLOYEEDELETE("/emp-list"),
	
	EMPLOYEEDETAIL("/employee/emp-detail");
	
	private final String endPoint;

	private EndPointEnum(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}
	
	
}
