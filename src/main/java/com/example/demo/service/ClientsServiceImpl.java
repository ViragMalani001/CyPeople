package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientsDAO;
import com.example.demo.entity.Clients;

import jakarta.transaction.Transactional;

@Service
public class ClientsServiceImpl implements ClientsService {
	
	private ClientsDAO clientsDAO;

	public ClientsServiceImpl(ClientsDAO clientsDAO) {
		super();
		this.clientsDAO = clientsDAO;
	}

	@Override
	public List<Clients> findClientsList() {
		
		return this.clientsDAO.findClientsList();
	}

	@Override
	public Clients findClientsById(int theId) {
		
		return this.clientsDAO.findClientsById(theId);
	}

	@Transactional
	@Override
	public Clients saveClientsList(Clients clients) {
		
		return this.clientsDAO.saveClientsList(clients);
	}

	@Transactional
	@Override
	public void deleteClientsById(int theId) {

		this.clientsDAO.deleteClientsById(theId);
	}

	
}
