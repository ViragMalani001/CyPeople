package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.AccountsService;

@Controller
public class AccountsController {

	private AccountsService accountsService;

	public AccountsController(AccountsService accountsService) {
		super();
		this.accountsService = accountsService;
	}
	
	@GetMapping("/accounts")
	public String showAccountsPage() {
		return "/accounts/accounts";
	}
	
	@GetMapping("/acc-payments")
	public String showAccountsPaymentsPage() {
		return "/accounts/acc-payments";
	}
}
