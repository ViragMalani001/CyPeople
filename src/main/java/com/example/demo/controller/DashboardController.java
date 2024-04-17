package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
import com.example.demo.service.DashboardService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	private EmployeeDAO employeeDAO;
	
	public DashboardController(DashboardService dashboardService, EmployeeDAO employeeDAO) {
		super();
		this.dashboardService = dashboardService;
		this.employeeDAO = employeeDAO;
	}



	@GetMapping("/dashboard")
	public String showDashboardPage(Model model, HttpSession session) {
		
		try {
			long employeeCount = this.dashboardService.employeeCount();
			long departmentCount = this.dashboardService.departmentCount();
			long clientCount = this.dashboardService.clientCount();
			long projectCont = this.dashboardService.projectsCount();
			long leaveRequestCount = this.dashboardService.leaveRequestCount();
			
			List<Object[]> departmentCoutn = this.dashboardService.departmentCountByDepartment();
			model.addAttribute("departments", departmentCoutn);
			
			List<Object[]> genderCount = this.dashboardService.genderCount();
			model.addAttribute("genderCount", genderCount);
			
			List<Employee> employees = this.employeeDAO.findAll();
			model.addAttribute("employeeList",employees);
			
			model.addAttribute("employeeCount", employeeCount);
			model.addAttribute("departmentCount", departmentCount);
			model.addAttribute("clientCount", clientCount);
			model.addAttribute("projectCount", projectCont);
			model.addAttribute("leaveRequestCount", leaveRequestCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "/dashboard/dashboard";
	}
	
	
}
