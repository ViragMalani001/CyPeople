package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.JPARepository.ClientsJPArepository;
import com.example.demo.dao.ClientsDAO;
import com.example.demo.entity.Clients;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ClientsServiceImpl implements ClientsService {
	
	private ClientsDAO clientsDAO;
	private ClientsJPArepository clientsJPArepository;

	public ClientsServiceImpl(ClientsDAO clientsDAO, ClientsJPArepository clientsJPArepository) {
		super();
		this.clientsDAO = clientsDAO;
		this.clientsJPArepository = clientsJPArepository;
	}

	@Override
	public List<Clients> findClientsList() {
		
		return this.clientsDAO.findClientsList();
	}

	@Override
	public Clients findClientsById(int theId) {
		
		return this.clientsDAO.findClientsById(theId);
	}

	@Override
	public Clients saveClientsList(Clients clients) {
		
		return this.clientsDAO.saveClientsList(clients);
	}

	@Override
	public void deleteClientsById(int theId) {

		this.clientsDAO.deleteClientsById(theId);
	}

	@Override
	public List<Clients> getClientsDetails() {
		return this.clientsJPArepository.getClientsDetails();
	}

	@Override
	public Clients findClientDetail(int id) {
		return this.clientsJPArepository.findClientDetail(id);
	}


	
}
