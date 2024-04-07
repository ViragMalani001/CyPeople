package com.example.demo.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.demo.entity.Reports;
import com.example.demo.service.ReportsService;


@Controller
public class ReportsController {
	
	private ReportsService reportsService;

	public ReportsController(ReportsService reportsService) {
		super();
		this.reportsService = reportsService;
	}

	@GetMapping("/reports")
	public String showReportsPage(Model theModel) {
		
		List<Reports> reportsList = this.reportsService.findAll();
		
		theModel.addAttribute("reportsList",reportsList);
		
		return "/reports/reports";
	}
	
	@GetMapping("/add-reports-expense")
	public String showAddReportsPage(Model theModel){

		Reports reports = new Reports();
		
		theModel.addAttribute("reportsList",reports);
		return "/reports/add-reports-expense";
	}
	
	@PostMapping("/add-reports-expense")
	public String showSaveReportsDetai(@ModelAttribute("reportsList") Reports reports, Model themodel ) {
		
		this.reportsService.saveDetails(reports);
		return "redirect:/reports";
	}
	
	@GetMapping("/reports-update")
	public String showReportsUpdateForm(@RequestParam("reportsId") int theId, Model theModel) {
		
		Reports reports = this.reportsService.findById(theId);
		
		theModel.addAttribute("reportsList", reports);
		return "/reports/add-reports-expense";
	}
	
	@GetMapping("/reports-delete")
	public String shwoReportsDeetePage(@RequestParam("reportsId") int theId, Model theModel) {
		
		this.reportsService.deleteById(theId);
		return "redirect:/reports";
	}
}
