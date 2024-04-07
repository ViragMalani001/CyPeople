package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Clients;

public interface ClientsDAO {

//	-------------  Client List --------------
	
	List<Clients> findClientsList();
	
	Clients findClientsById(int theId);
	
	Clients saveClientsList(Clients clients);
	
	void deleteClientsById(int theId);
}
