package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Enum.EndPointEnum;

@Controller
public class AccessDeniedController {

	String accessDeniedURL = EndPointEnum.ACCESSDENIED.getEndPoint();

	@GetMapping("/access-denied-clients")
	public String showAccessDeniedClietsPage() {
		return accessDeniedURL + "/access-denied-clients";
	}
	
	@GetMapping("/access-denied-projects")
	public String showAccessDeniedProjectsPage() {
		return accessDeniedURL + "/access-denied-projects";
	}
	
	@GetMapping("/access-denied-employees")
	public String showAccessDeniedEmployeesPage() {
		return accessDeniedURL + "/access-denied-employees";
	}
}
