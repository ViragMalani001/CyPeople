package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.dao.LoginDAO;
import com.example.demo.entity.Login;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	private LoginDAO loginDAO;

	public UserLoginServiceImpl(LoginDAO loginDAO) {
		super();
		this.loginDAO = loginDAO;
	}

	@Override
	public List<Login> findLoginUsersList() {
		
		return this.loginDAO.findLoginUsersList();
	}

}
