package com.example.demo.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.demo.Enum.EndPointEnum;
import com.example.demo.entity.Reports;
import com.example.demo.service.ReportsService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ReportsController {
	
	private ReportsService reportsService;

	public ReportsController(ReportsService reportsService) {
		super();
		this.reportsService = reportsService;
	}

	String reportsURL = EndPointEnum.REPORTS.getEndPoint();
	
	@GetMapping("/reports")
	public String showReportsPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		List<Reports> reportsList = this.reportsService.findAll();
		model.addAttribute("reportsList",reportsList);
		
		return reportsURL + "/reports";
	}
	
	@GetMapping("/add-reports-expense")
	public String showAddReportsPage(Model model, HttpSession session){

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		Reports reports = new Reports();
		model.addAttribute("reportsList",reports);
		return reportsURL + "/add-reports-expense";
	}
	
	@PostMapping("/add-reports-expense")
	public String showSaveReportsDetai(@ModelAttribute("reportsList") Reports reports, Model model) {
		
		this.reportsService.saveDetails(reports);
		return "redirect:/reports";
	}
	
	@GetMapping("/reports-update")
	public String showReportsUpdateForm(@RequestParam("reportsId") int theId, Model model, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		Reports reports = this.reportsService.findById(theId);
		model.addAttribute("reportsList", reports);
		return reportsURL + "/add-reports-expense";
	}
	
	@GetMapping("/reports-delete")
	public String shwoReportsDeetePage(@RequestParam("reportsId") int theId, Model model, HttpSession session) {
		
		this.reportsService.deleteById(theId);
		return "redirect:/reports";
	}
}
