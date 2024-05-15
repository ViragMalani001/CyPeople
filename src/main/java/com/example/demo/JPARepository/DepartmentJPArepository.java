package com.example.demo.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Department;

public interface DepartmentJPArepository extends JpaRepository<Department, Integer>{

	@Query(value = "SELECT countDepHead()", nativeQuery = true)
	public int countDepartment();
}
