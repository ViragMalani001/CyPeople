package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DashboardDAO;

@Service
public class DashboardServiceImpl implements DashboardService {

	private DashboardDAO dashboardDAO;

	public DashboardServiceImpl(DashboardDAO dashboardDAO) {
		super();
		this.dashboardDAO = dashboardDAO;
	}

	@Override
	public long employeeCount() {
		return this.dashboardDAO.employeeCount();
	}

	@Override
	public long departmentCount() {
		return this.dashboardDAO.departmentCount();
	}

	@Override
	public long clientCount() {
		
		return this.dashboardDAO.clientCount();
	}

	@Override
	public long projectsCount() {
		
		return this.dashboardDAO.projectsCount();
	}

	@Override
	public long leaveRequestCount() {
		
		return this.dashboardDAO.leaveRequestCount();
	}

	@Override
	public List<Object[]> departmentCountByDepartment() {
		return this.dashboardDAO.departmentCountByDepartment();
	}

	@Override
	public List<Object[]> genderCount() {
		
		return this.dashboardDAO.genderCount();
	}
	
}
