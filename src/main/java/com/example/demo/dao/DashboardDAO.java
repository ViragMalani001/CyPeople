package com.example.demo.dao;

import java.util.List;

public interface DashboardDAO {

	long employeeCount();
	
	long departmentCount();
	
	long clientCount();
	
	long projectsCount();
	
	long leaveRequestCount();
	
	List<Object[]> departmentCountByDepartment();
	
	List<Object[]> genderCount();
}
