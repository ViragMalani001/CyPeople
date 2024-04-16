package com.example.demo.service;

import java.util.List;

public interface DashboardService {

	long employeeCount();

	long departmentCount();
	
	long clientCount();
	
	long projectsCount();
	
	long leaveRequestCount();
	
	List<Object[]> departmentCountByDepartment();
	
	List<Object[]> genderCount();
}

