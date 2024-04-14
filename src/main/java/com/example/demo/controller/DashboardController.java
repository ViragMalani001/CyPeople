package com.example.demo.controller;

import java.util.LinkedHashMap;

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
		
		long employeeCount = this.dashboardService.employeeCount();
		long departmentCount = this.dashboardService.departmentCount();
		long clientCount = this.dashboardService.clientCount();
		long projectCont = this.dashboardService.projectsCount();
		long leaveRequestCount = this.dashboardService.leaveRequestCount();
		
		model.addAttribute("employeeCount", employeeCount);
		model.addAttribute("departmentCount", departmentCount);
		model.addAttribute("clientCount", clientCount);
		model.addAttribute("projectCount", projectCont);
		model.addAttribute("leaveRequestCount", leaveRequestCount);
		
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("2016", 147);
        graphData.put("2017", 1256);
        graphData.put("2018", 3856);
        graphData.put("2019", 19807);
        model.addAttribute("chartData", graphData);
		
		return "/dashboard/dashboard";
	}
	
	
	
	
}
