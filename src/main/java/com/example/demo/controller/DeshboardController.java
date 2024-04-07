package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.DashboardService;


@Controller
public class DeshboardController {

	private DashboardService dashboardService;

	public DeshboardController(DashboardService dashboardService) {
		super();
		this.dashboardService = dashboardService;
	}
	
	@GetMapping("/dashboard")
	public String showDashboardPage(Model model) {
		
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
		
		
		
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("Java", 40);
		surveyMap.put("Dev oops", 25);
		surveyMap.put("Python", 20);
		surveyMap.put(".Net", 15);
		model.addAttribute("surveyMap", surveyMap);
		
		model.addAttribute("pass", 50);
		model.addAttribute("fail", 50);
		
		return "/dashboard/dashboard";
	}
	
	
//	@GetMapping("/displayBarGraph")
//	public String barGraph(Model model) {
//		Map<String, Integer> surveyMap = new LinkedHashMap<>();
//		surveyMap.put("Java", 40);
//		surveyMap.put("Dev oops", 25);
//		surveyMap.put("Python", 20);
//		surveyMap.put(".Net", 15);
//		model.addAttribute("surveyMap", surveyMap);
//		return "barGraph";
//	}
//
//	@GetMapping("/displayPieChart")
//	public String pieChart(Model model) {
//		model.addAttribute("pass", 50);
//		model.addAttribute("fail", 50);
//		return "pieChart";
//	}
	
	
	
}
