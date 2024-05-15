package com.example.demo.controller;

import java.security.Principal;
import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.dao.ToDoMessageDAO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.ToDoMessage;
import com.example.demo.service.DashboardService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	private EmployeeDAO employeeDAO;
	private ToDoMessageDAO toDoMessageDAO;

	public DashboardController(DashboardService dashboardService, EmployeeDAO employeeDAO,
			ToDoMessageDAO ToDoMessageDAO) {
		super();
		this.dashboardService = dashboardService;
		this.employeeDAO = employeeDAO;
		this.toDoMessageDAO = ToDoMessageDAO;
	}

	@GetMapping("/dashboard")
	public String showDashboardPage(Principal principal, Authentication auth, Model model, HttpSession session) {
		
//		 String userName1 = principal.getName();
//		 Collection<? extends GrantedAuthority> authorities1 = auth.getAuthorities();
//		 
//		 System.out.println(userName1);
//		 System.out.println(authorities1);
		 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			Set CurrentUserName Session
		String currentUser = authentication.getName();
		session.setAttribute("username", currentUser);

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		 
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		System.out.println(currentUser);
		System.out.println(authorities);
//			Set CurrentUserName authority Session
		for (GrantedAuthority authority : authorities) {
			String authorityName = authority.getAuthority();

			session.setAttribute("userAuthority", authorityName);
			String currentUserAuthority = (String) session.getAttribute("userAuthority");
			String userAuthority = currentUserAuthority.substring(5);
			session.setAttribute("role", userAuthority);
			model.addAttribute("userAuthority", userAuthority);
		}

		long employeeCount = this.dashboardService.employeeCount();
		model.addAttribute("employeeCount", employeeCount);

		long departmentCount = this.dashboardService.departmentCount();
		model.addAttribute("departmentCount", departmentCount);

		long clientCount = this.dashboardService.clientCount();
		model.addAttribute("clientCount", clientCount);

		long projectCont = this.dashboardService.projectsCount();
		model.addAttribute("projectCount", projectCont);

		long leaveRequestCount = this.dashboardService.leaveRequestCount();
		model.addAttribute("leaveRequestCount", leaveRequestCount);

		List<Object[]> departmentCoutn = this.dashboardService.departmentCountByDepartment();
		model.addAttribute("departments", departmentCoutn);

		List<Object[]> genderCount = this.dashboardService.genderCount();
		model.addAttribute("genderCount", genderCount);

		List<Employee> employees = this.employeeDAO.findAll();
		model.addAttribute("employeeList", employees);

		List<ToDoMessage> message = this.toDoMessageDAO.findAll();
		model.addAttribute("message", message);

		ToDoMessage toDoMessage = new ToDoMessage();
		model.addAttribute("toDoMessage", toDoMessage);

		return "/dashboard/dashboard";
	}

	@PostMapping("/toDoMessage")
	public String toDoMessageShow(@ModelAttribute("toDoMessage") ToDoMessage toDoMessage) {

		this.toDoMessageDAO.save(toDoMessage);
		return "redirect:/dashboard";
	}

}
