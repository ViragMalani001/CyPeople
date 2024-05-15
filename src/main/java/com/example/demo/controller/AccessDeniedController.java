package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {


	@GetMapping("/access-denied-clients")
	public String showAccessDeniedClietsPage() {
		String a = "";
		return "/access-denied-page/access-denied-clients";
	}
	
	@GetMapping("/access-denied-projects")
	public String showAccessDeniedProjectsPage() {
		return "/access-denied-page/access-denied-projects";
	}
	
	@GetMapping("/access-denied-employees")
	public String showAccessDeniedEmployeesPage() {
		return "/access-denied-page/access-denied-employees";
	}
}
