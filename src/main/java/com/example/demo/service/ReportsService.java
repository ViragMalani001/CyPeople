package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Reports;

public interface ReportsService {
	
	List<Reports> findAll();
	
	Reports saveDetails(Reports reports);

	Reports findById(int theId);
	
	void deleteById(int theId);
}
