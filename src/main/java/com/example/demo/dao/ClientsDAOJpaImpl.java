package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Clients;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ClientsDAOJpaImpl implements ClientsDAO {
	
	private EntityManager entityManager;

	public ClientsDAOJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Clients> findClientsList() {
		
		TypedQuery<Clients> theQuery = entityManager.createQuery("FROM Clients", Clients.class);
		List<Clients> findClientsList = theQuery.getResultList();
		return findClientsList;
	}

	@Override
	public Clients findClientsById(int theId) {

		Clients findClientsById = entityManager.find(Clients.class, theId);
		return findClientsById;
	}

	@Override
	public Clients saveClientsList(Clients clients) {
		
		Clients saveClientsList = entityManager.merge(clients);
		return saveClientsList;
	}

	@Override
	public void deleteClientsById(int theId) {
		
		Clients deleteClientsById = entityManager.find(Clients.class, theId);
		entityManager.remove(deleteClientsById);
		
	}

}
