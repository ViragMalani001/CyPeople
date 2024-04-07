package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Clients;

public interface ClientsService {

	List<Clients> findClientsList();
	
	Clients findClientsById(int theId);
	
	Clients saveClientsList(Clients clients);
	
	void deleteClientsById(int theId);
}
