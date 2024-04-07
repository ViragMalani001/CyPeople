package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ReportsDAO;
import com.example.demo.entity.Reports;

import jakarta.transaction.Transactional;

@Service
public class ReportsServiceImpl implements ReportsService {

	private ReportsDAO reportsDAO;
	
	public ReportsServiceImpl(ReportsDAO reportsDAO) {
		super();
		this.reportsDAO = reportsDAO;
	}

	@Override
	public List<Reports> findAll() {

		return this.reportsDAO.findAll();
	}

	@Transactional
	@Override
	public Reports saveDetails(Reports reports) {
		
		return this.reportsDAO.saveDetails(reports);
	}

	@Override
	public Reports findById(int theId) {
		
		return this.reportsDAO.findById(theId);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {

		this.reportsDAO.deleteById(theId);
	}

}
