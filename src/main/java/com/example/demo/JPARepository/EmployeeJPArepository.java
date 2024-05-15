package com.example.demo.JPARepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeJPArepository extends JpaRepository<Employee, Integer>{

	@Query(value = "SELECT countEmp()", nativeQuery = true)
	public int countEmployee();

}
