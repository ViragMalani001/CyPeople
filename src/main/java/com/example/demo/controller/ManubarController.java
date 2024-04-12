package com.example.demo.controller;

import java.util.Collection;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.entity.Registration;
import com.example.demo.service.DashboardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManubarController {
	
	private static final String Registration = null;
	private DashboardService dashboardService;

	public ManubarController(DashboardService dashboardService) {
		super();
		this.dashboardService = dashboardService;
	}

	@GetMapping("/cypeople")
	public String showCyPeoplePage(Model model) {
		
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
		
		return "/fragment/dashboard";
	}
	
	@GetMapping("/manubar")
	public String showHeaderPage(HttpSession session , Registration login, Model theModel) {

//		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//		Collection<? extends GrantedAuthority> currentUserAuthority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//		Set CurrentUserName Session
		String currentUser = authentication.getName();
		session.setAttribute("username", currentUser);

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String userName = (String) session.getAttribute("username");
		theModel.addAttribute("username",userName);
		
//		Set CurrentUserName authority Session
		for (GrantedAuthority authority : authorities) {
		    String authorityName = authority.getAuthority();
		    
		    session.setAttribute("userAuthority", authorityName);
		    
		    String currentUserAuthority = (String) session.getAttribute("userAuthority");
		    String userAuthority = currentUserAuthority.substring(5);
		    theModel.addAttribute("userAuthority",userAuthority);
		    
		    
		}
		
		return "/manubar";
	}
}
