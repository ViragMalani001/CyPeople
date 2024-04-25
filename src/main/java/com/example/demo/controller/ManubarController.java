package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManubarController {

	@GetMapping("/cypeople")
	public String showCyPeoplePage() {
		
		return "/dashboard/dashboard";
	}

//	@GetMapping("/manubar")
//	public String showHeaderPage(HttpSession session , Registration login, Model model) {
//
//		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//		model.addAttribute("username",currentUser);
//		
//		Collection<? extends GrantedAuthority> currentUserAuthority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//		model.addAttribute("userAuthority",currentUserAuthority);
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Set CurrentUserName Session
//		String currentUser = authentication.getName();
//		session.setAttribute("username", currentUser);
//		
//		String userName = (String) session.getAttribute("username");
//
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		
//		Set CurrentUserName authority Session
//		for (GrantedAuthority authority : authorities) {
//		    String authorityName = authority.getAuthority();
//		    
//		    session.setAttribute("userAuthority", authorityName);
//		    String currentUserAuthority = (String) session.getAttribute("userAuthority");
//		    String userAuthority = currentUserAuthority.substring(5);
//		    model.addAttribute("userAuthority",userAuthority);
//		    
//		    session.setAttribute("userNameAuthority", userAuthority);
//		    
//		    
//		}
//		
//		return "/manubar";
//	}
}
