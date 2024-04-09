package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Registration;

public interface LoginDAO {

	
	List<Registration> findLoginUsersList();

}
