package com.example.demo.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Registration;

public interface RegistrationJPARepository extends JpaRepository<Registration, Integer> {

//	@Query("SELECT r FROM Registration r WHERE r.email =:email")
//	public Registration getUserByEmail(@RequestParam("email") String email);
//	
//	@Query("SELECT name FROM Registration r WHERE r.email =:email")
//	public Registration getNameByEmail(@RequestParam("email") String email);
}
