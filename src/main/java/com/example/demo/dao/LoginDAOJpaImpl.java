package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Login;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class LoginDAOJpaImpl implements LoginDAO {

	private EntityManager entityManager;

	public LoginDAOJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Login> findLoginUsersList() {
		
		TypedQuery<Login> theQuery = entityManager.createQuery("FROM Login", Login.class);
		List<Login> findLoginUsersList = theQuery.getResultList();
		return findLoginUsersList;
	}
	
	
}
