package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.AttendanceCount;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveRequest;

public interface EmployeeService {

	
//	----------------- Employee List ----------------------
	List<Employee> findAll();

	Employee findById(int theId);
	
	Employee save(Employee theEmpoyee);
	
	void deleteById(int theId);
	
	List<Employee> findByCurrentUser(String currentUser);

	String findByCurrentUserId(String currentUser);
	
	String findByCurrentUserDepartment(String currentUser);
	
	List<Object[]> groupByRole();


	
//	-------------------- LeaveRequest List --------------------

	List<LeaveRequest> findLeaveList();
	
	LeaveRequest findLeaveRequestId(int theId);
	
	LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest);
	
	void deleteLeaveRequestById(int theId);
	
	void approveLeaveRequest(int employeeId);
	
	void rejectLeaveRequest(int employeeId);
	
//	---------------- Attendance List -------------------------
	
	List<AttendanceCount> findAttendanceList();
	
	AttendanceCount findAttendanceById(int theId);
	
	void saveAttendanceCount(AttendanceCount attendanceCount);
	
	List<AttendanceCount> findAttendanceByUsername(String username);
	
	long attendanceCount(String username);

	String attendanceHoursCount(String username);
	
	AttendanceCount getAttendanceCountByDate(LocalDate date, String username);


//	----------------------- Department List ---------------------
	
	List<Department> findDepartmentList();
	
	Department findeDepartmentById(int theId);
	
	Department saveDepartmentList(Department department);
	
	void deleteDepartmentById(int theId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
