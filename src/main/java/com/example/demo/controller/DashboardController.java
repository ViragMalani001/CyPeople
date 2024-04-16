package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.service.DashboardService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DashboardController {

	private DashboardService dashboardService;

	public DashboardController(DashboardService dashboardService) {
		super();
		this.dashboardService = dashboardService;
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
