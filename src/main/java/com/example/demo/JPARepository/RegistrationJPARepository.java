package com.example.demo.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Registration;

public interface RegistrationJPARepository extends JpaRepository<Registration, Integer> {

	@Query("SELECT r FROM Registration r WHERE r.email =:email")
	public Registration getUserByEmail(@Param("email") String email);
}
