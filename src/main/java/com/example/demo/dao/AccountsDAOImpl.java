package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class AccountsDAOImpl implements AccountsDAO {

	private EntityManager entityManager;

	public AccountsDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	
}
