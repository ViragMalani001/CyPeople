package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginForm(HttpServletRequest httpSession, HttpSession session, Login login) {
				
//		HttpSession session = httpSession.getSession();
//		session.setAttribute("username", "viragMalani");
		return "login";
	}
	
	@GetMapping("/createAccount")
	public String showCreateAccountPage() {
		
		return "/createAccount";
	}
	
	
	
	

	
	
	
	
	
}
