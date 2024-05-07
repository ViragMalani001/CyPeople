package com.example.demo.dao;

import java.time.LocalDate;


import java.util.List;

import com.example.demo.entity.AttendanceCount;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Department;
import com.example.demo.entity.LeaveRequest;


public interface EmployeeDAO {
	
//------------- Employee ------------------
	
	List<Employee> findAll();
	
	Employee findById(int theId);
	
	Employee save(Employee theEmployee);
	
	Employee merge(int theId);
	
	void deleteById(int theId);
	
	List<Employee> findByCurrentUser(String currentUser);
	
	String findByCurrentUserId(String currentUser);
	
	String findByCurrentUserDepartment(String currentUser);
	
	List<Object[]> groupByRole();
	
//	-------------- Leave Request ------------------
	
	List<LeaveRequest> findLeaveList();
	
	LeaveRequest findLeaveRequestById(int theId);
	
	LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest);
	
//	List<LeaveRequestRightJoin> empName();
	
	List<LeaveRequest> empNameNew();
	
	void deleteLeaveRequestById(int theId);
	
	
//	-------------- Attendance Count --------------------
	
	List<AttendanceCount> findAttendanceList();
	
	AttendanceCount findAttendanceById(int theId);
	
	void saveAttendanceCount(AttendanceCount attendanceCount);
	
	List<AttendanceCount> findAttendanceListByUsername(String username);
	
	long attendanceCount(String username);
	
	String attendanceHoursCount(String username);
	
	AttendanceCount getAttendanceCountByDate(LocalDate date, String usename);
	
//	------------------------ Department List --------------------------
	
	List<Department> findDepartmentList();
	
	Department findeDepartmentById(int theId);
	
	Department saveDepartmentList(Department department);
	
	void deleteDepartmentById(int theId);
}
