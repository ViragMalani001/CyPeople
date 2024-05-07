package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.JPARepository.LeaveRequestJPArepository;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.AttendanceCount;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveRequest;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;
	private LeaveRequestJPArepository leaveRequestJPArepository;

	public EmployeeServiceImpl(EmployeeDAO employeeDAO, LeaveRequestJPArepository leaveRequestJPArepository) {
		super();
		this.employeeDAO = employeeDAO;
		this.leaveRequestJPArepository = leaveRequestJPArepository;
	}

//	-------------- Employee List  -------------------------
	@Override
	public List<Employee> findAll() {
		return this.employeeDAO.findAll();
	}

	@Override
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}
	

	@Transactional
	@Override
	public Employee save(Employee theEmpoyee) {
		return this.employeeDAO.save(theEmpoyee);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		this.employeeDAO.deleteById(theId);
	}
	
	@Override
	public List<Employee> findByCurrentUser(String currentUser) {
		
		return this.employeeDAO.findByCurrentUser(currentUser);
	}
	
	@Override
	public String findByCurrentUserId(String currentUser) {
		
		return this.employeeDAO.findByCurrentUserId(currentUser);
	}

	@Override
	public List<Object[]> groupByRole() {
		
		List<Object[]> groupByRole = this.employeeDAO.groupByRole();
		return groupByRole;
	}

	@Override
	public String findByCurrentUserDepartment(String currentUser) {
		return this.employeeDAO.findByCurrentUserDepartment(currentUser);
	}

	
//	----------------- LeaveRequest ------------
	@Override
	public List<LeaveRequest> findLeaveList() {
		
		return this.employeeDAO.findLeaveList();
	}

	@Override
	public LeaveRequest findLeaveRequestId(int theId) {
		
		return this.employeeDAO.findLeaveRequestById(theId);
	}

	@Transactional
	@Override
	public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
		
		return this.employeeDAO.saveLeaveRequest(leaveRequest);
	}

	@Override
	public void deleteLeaveRequestById(int theId) {
		
		this.employeeDAO.deleteLeaveRequestById(theId);
	}

	@Override
	public void approveLeaveRequest(int employeeId) {
        LeaveRequest leaveAction = this.leaveRequestJPArepository.findById(employeeId).orElse(null);
        if (leaveAction != null) {
        	leaveAction.setApprovalStatus("Approved");
            this.leaveRequestJPArepository.save(leaveAction);
        }		
	}

	@Override
	public void rejectLeaveRequest(int employeeId) {
		LeaveRequest leaveAction = this.leaveRequestJPArepository.findById(employeeId).orElse(null);
        if (leaveAction != null) {
        	leaveAction.setApprovalStatus("Rejected");
            this.leaveRequestJPArepository.save(leaveAction);
        }		
	}

	
//	----------------  Attendance Count -----------------------
	@Override
	public List<AttendanceCount> findAttendanceList() {
		
		return this.employeeDAO.findAttendanceList();
	}

	@Override
	public AttendanceCount findAttendanceById(int theId) {

		return this.employeeDAO.findAttendanceById(theId);
	}

	@Transactional
	@Override
	public void saveAttendanceCount(AttendanceCount attendanceCount) {

		this.employeeDAO.saveAttendanceCount(attendanceCount);
	}

	@Override
	public List<AttendanceCount> findAttendanceByUsername(String username) {

		return this.employeeDAO.findAttendanceListByUsername(username);
	}

	@Override
	public long attendanceCount(String username) {

		return this.employeeDAO.attendanceCount(username);
	}

	@Override
	public String attendanceHoursCount(String username) {

		return this.employeeDAO.attendanceHoursCount(username);
	}
	
	@Override
	public AttendanceCount getAttendanceCountByDate(LocalDate date, String username) {
		
		return this.employeeDAO.getAttendanceCountByDate(date, username);
	}

	
//	--------------------  Department List ---------------
	@Override
	public List<Department> findDepartmentList() {

		return this.employeeDAO.findDepartmentList();
	}

	@Override
	public Department findeDepartmentById(int theId) {
		
		return this.employeeDAO.findeDepartmentById(theId);
	}

	@Transactional
	@Override
	public Department saveDepartmentList(Department department) {
		
		return this.employeeDAO.saveDepartmentList(department);
		
	}

	@Transactional
	@Override
	public void deleteDepartmentById(int theId) {

		this.employeeDAO.deleteDepartmentById(theId);
		
	}













	
}







