package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountsDAO;

@Service
public class AccountsServiceImpl implements AccountsService  {
	
	private AccountsDAO accountsDAO;

	public AccountsServiceImpl(AccountsDAO accountsDAO) {
		super();
		this.accountsDAO = accountsDAO;
	}
	
	

}
