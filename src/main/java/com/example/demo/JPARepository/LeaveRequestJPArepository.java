package com.example.demo.JPARepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeaveRequest;

public interface LeaveRequestJPArepository extends JpaRepository<LeaveRequest, Integer> {

	Optional<LeaveRequest> findById(int employeeId);
}
