package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Enum.EndPointEnum;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.payrollService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayRollController {
	
	private EmployeeService employeeService;
	private payrollService payrollService;
	
	public PayRollController(EmployeeService employeeService, payrollService payrollService) {
		super();
		this.employeeService = employeeService;
		this.payrollService = payrollService;
	}
	
	String payrollURL = EndPointEnum.PAYROLL.getEndPoint();

	@GetMapping("/payroll")
	public String showPayrollsPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		return payrollURL + "/payroll";
	}
	
	@GetMapping("/payslip")
	public String showPaySlipPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		return payrollURL + "/payslip";
	}
	
	@GetMapping("/emp-salary")
	public String showEmpSalaryPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		String totalHours = this.employeeService.attendanceHoursCount(currentUser);
		List<Employee> currentUserName = this.employeeService.findByCurrentUser(currentUser);		
        
		List<Employee> employeeList = this.employeeService.findAll();
		
		int totalHoursInt = Integer.parseInt(totalHours);
		double hourlyRate = 148;
		double totalSalary = totalHoursInt * hourlyRate;
		
		model.addAttribute("totalHours", totalHours);
		model.addAttribute("totalSalary",totalSalary);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("currentUser",currentUserName);
		return payrollURL + "/emp-salary";
	}
	
	@GetMapping("/generatePaySlipe")
	public ResponseEntity<InputStreamResource> generatePaySlipPdf(@RequestParam("employeeId") int theId) {
		
		ByteArrayInputStream pdf = payrollService.generatePaySlip(theId);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("content-diposition", "inline;file=payslip.pdf");
		
		
		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
	}
	
	
	
	
	
	
	
}
