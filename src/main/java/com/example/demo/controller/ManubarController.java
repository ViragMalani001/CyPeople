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
	
	private DashboardService dashboardService;

	public ManubarController(DashboardService dashboardService) {
		super();
		this.dashboardService = dashboardService;
	}

	@GetMapping("/cypeople")
	public String showCyPeoplePage(Model model) {
		
		return "/dashboard/dashboard";
	}
	
	@GetMapping("/manubar")
	public String showHeaderPage(HttpSession session , Registration login, Model model) {

//		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//		Collection<? extends GrantedAuthority> currentUserAuthority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Set CurrentUserName Session
		String currentUser = authentication.getName();
		session.setAttribute("username", currentUser);
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		
//		Set CurrentUserName authority Session
		for (GrantedAuthority authority : authorities) {
		    String authorityName = authority.getAuthority();
		    
		    session.setAttribute("userAuthority", authorityName);
		    String currentUserAuthority = (String) session.getAttribute("userAuthority");
		    String userAuthority = currentUserAuthority.substring(5);
		    model.addAttribute("userAuthority",userAuthority);
		    
		    session.setAttribute("userNameAuthority", userAuthority);
		    
		    
		}
		
		return "/manubar";
	}
}
