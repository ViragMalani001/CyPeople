package com.example.demo.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Registration;

import jakarta.servlet.http.HttpSession;

@Controller
public class HeaderController {


	@GetMapping("/cypeople")
	public String showLoginFrom() {
		return "cypeople";
	}
	
	@GetMapping("/header")
	public String showHeaderPage(HttpSession session , Registration login, Model theModel) {

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
}
