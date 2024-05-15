package com.example.demo.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Enum.EndPointEnum;
import com.example.demo.JPARepository.ClientsJPArepository;
import com.example.demo.entity.Clients;
import com.example.demo.service.ClientsService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ClientController {
	
	private ClientsService clientsService;
	private ClientsJPArepository clientsJPArepository;

	public ClientController(ClientsService clientsService, ClientsJPArepository clientsJPArepository) {
		super();
		this.clientsService = clientsService;
		this.clientsJPArepository = clientsJPArepository;
	}

//	ENUM ENDPOINT
	String clientsURL = EndPointEnum.CLIENTS.getEndPoint();
	
	// ----------------------------- Clients Page ------------------------------
	@GetMapping("/clients")
	public String showClientsPage(HttpSession session, Model model) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		return clientsURL + "/clients";
	}
	
	@GetMapping("/clients-list")
	public String showClientsListPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("username");
		model.addAttribute("userAuthority",currentUserAuthority);
		
//		List<Clients> clients = this.clientsService.findClientsList();
//		model.addAttribute("clients",clients);
		
		List<Clients> clients = this.clientsService.getClientsDetails();
		model.addAttribute("clients",clients);
		
		return clientsURL + "/clients-list";
	}
	
	@GetMapping("/add-clients")
	public String showAddClientsPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		Clients clients = new Clients();
		model.addAttribute(clients);
		return clientsURL + "/add-clients";
	}
	
//	not using stored procedure
//	
//	@PostMapping("/add-clients")
//	public String clientsDetailsSave(@Valid @ModelAttribute("clients") Clients clients, BindingResult theBindingResult, HttpSession session, Model model,
//			@RequestParam("id") int id) {
//		
//		String userName = (String) session.getAttribute("username");
//		model.addAttribute("username",userName);
//		
//		String currentUserAuthority = (String) session.getAttribute("role");
//		model.addAttribute("userAuthority",currentUserAuthority);
//		
//		if(theBindingResult.hasErrors()) {
//			return "/clients/add-clients";
//		}
//		this.clientsService.saveClientsList(clients);
//		return "redirect:/clients-list";
//	}
	
//	
	@PostMapping("/add-clients")
	public String clientsDetailsSave(@RequestParam("clientName") String clientName, @RequestParam("companyName") String companyName,
			@RequestParam("clientId") String clientId, @RequestParam("email") String email, @RequestParam("mobileNo") String mobileNo, HttpSession session, Model model
			) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);

		this.clientsJPArepository.saveClientsDetails(clientName, companyName, clientId, email, mobileNo);
		return "redirect:/clients-list";
	}
	
	@GetMapping("/clients-update")
	public String empUpdate(@RequestParam("clientId") int theId, Model model, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
//		Clients theClients = this.clientsService.findClientsById(theId);
		
		Clients theClients = this.clientsService.findClientDetail(theId);
		model.addAttribute("clients", theClients);
		
		return clientsURL + "/add-clients";
	}
	
	@GetMapping("/clients-delete")
	public String deleteClient(@RequestParam("clientId") int theId) {
		
		this.clientsService.deleteClientsById(theId);
		return "redirect:/clients-list";
	}
	


}
