package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reports;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ReportsDAOJpalmpl implements ReportsDAO {
	
	private EntityManager entityManager;

	public ReportsDAOJpalmpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	public List<Reports> findAll() {
		
		TypedQuery<Reports> theQuery = entityManager.createQuery("FROM Reports", Reports.class);
		
		List<Reports> reportsDetails = theQuery.getResultList();
		return reportsDetails;
	}

	@Override
	public Reports saveDetails(Reports reports) {
		
		Reports saveReports = this.entityManager.merge(reports); 
		return saveReports;
	}


	@Override
	public Reports findById(int theId) {
		
		Reports reports = entityManager.find(Reports.class, theId);
		return reports;
	}


	@Override
	public void deleteById(int theId) {
		
		Reports deleteReports = this.entityManager.find(Reports.class, theId);
		entityManager.remove(deleteReports);
	}

	

}
