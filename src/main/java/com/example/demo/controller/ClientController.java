package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Clients;
import com.example.demo.service.ClientsService;

import jakarta.validation.Valid;

@Controller
public class ClientController {
	
	private ClientsService clientsService;

	public ClientController(ClientsService clientsService) {
		super();
		this.clientsService = clientsService;
	}

	// ----------------------------- Clients Page ------------------------------
	@GetMapping("/clients")
	public String showClientsPage() {
		return "/clients/clients";
	}
	
	@GetMapping("/clients-list")
	public String showClientsListPage(Model model) {
				
		List<Clients> clients = this.clientsService.findClientsList();
		model.addAttribute("clients",clients);
		return "/clients/clients-list";
	}
	
	@GetMapping("/add-clients")
	public String showAddClientsPage(Model model) {
		
		Clients clients = new Clients();
		model.addAttribute(clients);
		return "/clients/add-clients";
	}
	
	@PostMapping("/add-clients")
	public String clientsDetailsSave(@Valid @ModelAttribute("clients") Clients clients, BindingResult theBindingResult) {
		
		
		if(theBindingResult.hasErrors()) {
			return "/clients/add-clients";
		}
		this.clientsService.saveClientsList(clients);
		return "redirect:/clients-list";
	}
	
	@GetMapping("/clients-update")
	public String empUpdate(@RequestParam("clientId") int theId, Model model) {

		Clients theClients = this.clientsService.findClientsById(theId);
		model.addAttribute("clients", theClients);
		return "clients/add-clients";
	}
	
	@GetMapping("/clients-delete")
	public String deleteClient(@RequestParam("clientId") int theId) {
		
		this.clientsService.deleteClientsById(theId);
		return "redirect:/clients-list";
	}
	


}
