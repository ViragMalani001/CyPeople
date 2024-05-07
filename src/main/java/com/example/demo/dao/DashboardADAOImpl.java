package com.example.demo.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class DashboardADAOImpl implements DashboardDAO {

	private EntityManager entityManager;

	public DashboardADAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public long employeeCount() {
		
		TypedQuery<Long> theQuery = entityManager.createQuery("SELECT count(name) FROM Employee", long.class);
		return theQuery.getSingleResult();
		
	}

	@Override
	public long departmentCount() {
		
		TypedQuery<Long> theQuery = entityManager.createQuery("SELECT count(departmentName) FROM Department", long.class);
		return theQuery.getSingleResult();
	}

	@Override
	public long clientCount() {
		
		TypedQuery<Long> theQuery = entityManager.createQuery("SELECT count(clientName) FROM Clients", long.class);
		return theQuery.getSingleResult();
	}

	@Override
	public long projectsCount() {
		
		TypedQuery<Long> theQuery = entityManager.createQuery("SELECT count(projectName) FROM Projects", long.class);
		return theQuery.getSingleResult();
	}

	@Override
	public long leaveRequestCount() {
		
		TypedQuery<Long> theQuery = entityManager.createQuery("SELECT count(id) FROM LeaveRequest", long.class);
		return theQuery.getSingleResult();
	}

	@Override
	public List<Object[]> departmentCountByDepartment() {
		
		TypedQuery<Object[]> query = entityManager.createQuery("SELECT COUNT(e.name), e.role FROM Employee e GROUP BY e.role", Object[].class);
		List<Object[]> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> genderCount() {
		
		TypedQuery<Object[]> query = entityManager.createQuery("SELECT COUNT(e.name), e.gender FROM Employee e GROUP BY gender ", Object[].class);
		List<Object[]> genderCount = query.getResultList();
		return genderCount;
	}
	
	
}
