package com.example.demo.JPARepository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Clients;

import jakarta.transaction.Transactional;

public interface ClientsJPArepository extends JpaRepository<Clients, Integer>{

//	QUERY annotation to call procedure
//	@Query(value = "CALL getClientsDetails", nativeQuery = true)
//	public List<Clients> getClientsDetails();
	
	@Procedure(name = "getClientsDetails")
	public List<Clients> getClientsDetails();

	@Procedure(name = "saveClientsDetails")
	void saveClientsDetails(@Param("clientName") String clientName, @Param("companyName") String companyName, @Param("clientId") String clientId, 
			@Param("email") String email, @Param("mobileNo") String mobileNo);
	
	@Procedure(name = "findClientDetail")
	public Clients findClientDetail(@Param("id") int id);
}
