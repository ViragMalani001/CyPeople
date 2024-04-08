package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SidebarController {

	@GetMapping("/sidebar")
	public String showSidebarPage() {
		return "/sidebar";
	}
}
