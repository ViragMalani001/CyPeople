package com.example.demo.controller;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginForm(HttpServletRequest httpSession, HttpSession session, Login login) {
				
//		HttpSession session = httpSession.getSession();
//		session.setAttribute("username", "viragMalani");
		return "login";
	}

	@GetMapping("/access-denied-clients")
	public String showAccessDeniedClietsPage() {
		return "access-denied-clients";
	}
	
	@GetMapping("/access-denied-projects")
	public String showAccessDeniedProjectsPage() {
		return "access-denied-projects";
	}
	
	@GetMapping("/access-denied-employees")
	public String showAccessDeniedEmployeesPage() {
		return "access-denied-employees";
	}
	
	@GetMapping("/cypeople")
	public String showLoginFrom() {
		return "cypeople";
	}

	@GetMapping("/header")
	public String showHeaderPage(HttpSession session , Login login, Model theModel) {

//		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//		Collection<? extends GrantedAuthority> currentUserAuthority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String currentUser = authentication.getName();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		session.setAttribute("username", currentUser);
		String userName = (String) session.getAttribute("username");
		
		theModel.addAttribute("username",userName);

		for (GrantedAuthority authority : authorities) {
		    String authorityName = authority.getAuthority();
		    
		    session.setAttribute("userAuthority", authorityName);
		    String currentUserAuthority = (String) session.getAttribute("userAuthority");
		    String userAuthority = currentUserAuthority.substring(5);
		    theModel.addAttribute("userAuthority",userAuthority);
		    
		}
		
		return "header";
	}
	
	@GetMapping("/sidebar")
	public String showSidebarPage() {
		return "/sidebar";
	}
	
	
	// ----------------------------- Setting Page ------------------------------
	@GetMapping("/setting")
	public String showSettingPage() {
		return "/setting/setting";
	}
	
	
}
