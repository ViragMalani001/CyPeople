package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.JPARepository.RegistrationJPARepository;
import com.example.demo.entity.Registration;
import com.example.demo.helper.Message;

import jakarta.servlet.http.HttpSession;


@Controller
public class RegistrationController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RegistrationJPARepository registrationJPARepository;
	
	@GetMapping("/login")
	public String showLoginForm() {

		return "login";
	}
	
	@GetMapping("/registration")
	public String showCreateAccountPage(Model model) {
		
		model.addAttribute("registrationDetail", new Registration());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String saveRegistrationDetail(@ModelAttribute("registrationDetail") Registration registration, Model model, HttpSession session){
		
		try {
			registration.setEnabled(true);
			registration.setPassword(passwordEncoder.encode(registration.getPassword()));
			Registration saveDetail = this.registrationJPARepository.save(registration);
//			model.addAttribute("registrationDetail", saveDetail);
//			model.addAttribute("registrationDetail", registration);
			session.setAttribute("message", new Message("Successfully Register.", "Success"));
			return "registration";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("registrationDetail", registration);
			session.setAttribute("message", new Message("Somthing went wrong!!"+ e.getMessage(),"Error"));
			return "registration";
		}

	}
	
	
	
	

	
	
	
	
	
}
