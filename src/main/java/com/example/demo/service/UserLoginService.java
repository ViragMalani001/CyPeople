package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Registration;

public interface UserLoginService {

	List<Registration> findLoginUsersList();
}
