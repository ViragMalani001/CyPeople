package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Reports;

public interface ReportsDAO {
	
	List<Reports> findAll();

	Reports saveDetails(Reports reports);
	
	Reports findById(int theId);
	
	void deleteById(int theId);
}
