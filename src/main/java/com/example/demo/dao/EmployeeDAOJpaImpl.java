package com.example.demo.dao;

import java.sql.Date;

import java.time.Duration;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AttendanceCount;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

// ------------ Employee List ---------------------------
	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);
		List<Employee> employeeList = theQuery.getResultList();
		return employeeList;
	}
	
	@Override
	public Employee findById(int theId) {
		Employee findById = entityManager.find(Employee.class, theId);
		return findById;
	}

	@Override
	public Employee merge(int theId) {
		return null;
	}

	@Override
	public Employee save(Employee theEmployee) {
		
		Employee saveEntry = entityManager.merge(theEmployee);
		return saveEntry;
	}


	@Override
	public void deleteById(int theId) {
		
		Employee deleteId = entityManager.find(Employee.class, theId);
		entityManager.remove(deleteId);
	}
	
	@Override
	public List<Employee> findByCurrentUser(String currentUser) {
		
		TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee WHERE name =:currentUser", Employee.class);
		
		theQuery.setParameter("currentUser", currentUser);
		List<Employee> currentUserName = theQuery.getResultList();
		return currentUserName;
	}
	
	@Override
	public String findByCurrentUserId(String currentUser) {
		
		TypedQuery<String> theQuery = entityManager.createQuery("SELECT employeeId FROM Employee WHERE name =:currentUser", String.class);
		
		theQuery.setParameter("currentUser", currentUser);
		String currentUserId = theQuery.getSingleResult();
		return currentUserId;
	}

	@Override
	public String findByCurrentUserDepartment(String currentUser) {
		TypedQuery<String> theQuery = entityManager.createQuery("SELECT role FROM Employee WHERE name =:currentUser", String.class);
		theQuery.setParameter("currentUser", currentUser);
		String currentUserRole = theQuery.getSingleResult();
		return currentUserRole;
	}
	
	@Override
	public List<Object[]> groupByRole() {
		
		TypedQuery<Object[]> theQuery = entityManager.createQuery("SELECT e.department.departmentName, COUNT(name) FROM Employee e GROUP BY e.e.department.departmentName", Object[].class);
		List<Object[]> groupByRole = theQuery.getResultList();
		return groupByRole;
	}

//	-----------------  Leave List ----------------------------
	@Override
	public List<LeaveRequest> findLeaveList() {
		
		TypedQuery<LeaveRequest> theQuery = entityManager.createQuery("FROM LeaveRequest", LeaveRequest.class);
		List<LeaveRequest> leaveList = theQuery.getResultList();
		return leaveList;
	}

	@Override
	public LeaveRequest findLeaveRequestById(int theId) {
		
		LeaveRequest findLeaveRequestId = entityManager.find(LeaveRequest.class, theId);
		return findLeaveRequestId;
	}

	@Override
	public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
		
		LeaveRequest saveLeaveRequest = entityManager.merge(leaveRequest);
		return saveLeaveRequest;
	}

	@Transactional
	@Override
	public void deleteLeaveRequestById(int theId) {
		
		LeaveRequest deleteLeaveRequest = entityManager.find(LeaveRequest.class, theId);
		entityManager.remove(deleteLeaveRequest);
	}


//	@Override
//	public List<LeaveRequestRightJoin> empName() {
//		TypedQuery<LeaveRequestRightJoin> query = entityManager.createQuery("select new com.example.demo.entity.LeaveRequestRightJoin(e.name, l.employeeId, l.startDate, l.endDate, l.reason, l.approvalStatus) from Employee e right join LeaveRequest l on e.employeeId = l.employeeId", LeaveRequestRightJoin.class);
//		List<LeaveRequestRightJoin> employeesNameList = query.getResultList();
//		return employeesNameList;
//	}
	
	@Override
	public List<LeaveRequest> empNameNew() {
		TypedQuery<Object[]> query = entityManager.createQuery("select e.name, l.id, l.employeeId, l.startDate, l.endDate, l.reason, l.approvalStatus from Employee e right join LeaveRequest l on e.employeeId = l.employeeId", Object[].class);
		List<Object[]> resultList = query.getResultList();
		
		List<LeaveRequest> employeesNameList = new ArrayList<>();

		for (Object[] result : resultList) {
		    LeaveRequest leaveRequest = new LeaveRequest();
		    
		    leaveRequest.setName((String) result[0]);	
		    leaveRequest.setId((int) result[1]) ;
		    leaveRequest.setEmployeeId((String) result[2]);
		    leaveRequest.setStartDate((Date) result[3]);
		    leaveRequest.setEndDate((Date) result[4]);
		    leaveRequest.setReason((String) result[5]);
		    leaveRequest.setApprovalStatus((String) result[6]);
		    
		    employeesNameList.add(leaveRequest);
		}

		return employeesNameList;
	}
	
//	---------------- Attendance Count List ------------
	@Override
	public List<AttendanceCount> findAttendanceList() {
		
		TypedQuery<AttendanceCount> theQuery = entityManager.createQuery("FROM AttendanceCount", AttendanceCount.class);
		List<AttendanceCount> findAttendanceList = theQuery.getResultList();
		return findAttendanceList;
	}

	@Override
	public AttendanceCount findAttendanceById(int theId) {
		
		AttendanceCount findAttendance = entityManager.find(AttendanceCount.class, theId);
		return findAttendance;
	}
	
	@Override
	public void saveAttendanceCount(AttendanceCount attendanceCount) {
		entityManager.merge(attendanceCount);
	}

	@Override
	public List<AttendanceCount> findAttendanceListByUsername(String username) {

		TypedQuery<AttendanceCount> theQuery = entityManager.createQuery("FROM AttendanceCount WHERE name =:username",AttendanceCount.class);
		
		theQuery.setParameter("username", username);
		List<AttendanceCount> attendanceListByUsername = theQuery.getResultList();
		return attendanceListByUsername;
	}

	@Override
	public long attendanceCount(String username) {

		TypedQuery<Long> theQuery = entityManager.createQuery("SELECT COUNT(name) FROM AttendanceCount WHERE name =:username", long.class);
        theQuery.setParameter("username", username);
        return  (long) theQuery.getSingleResult();
	}

	@Override
	public String attendanceHoursCount(String username) {
		TypedQuery<Object[]> theQuery = entityManager.createQuery("SELECT punchInTime, punchOutTime FROM AttendanceCount WHERE name =:username", Object[].class);
		theQuery.setParameter("username", username);
		
        List<Object[]> results = theQuery.getResultList();

        long totalPunchHours = 0;
        long totalPunchMinutes = 0;
        long totalPunchSeconds = 0;
        for (Object[] result : results) {
            if (result[0] != null && result[1] != null) {
                LocalTime punchInTime = (LocalTime) result[0];
                LocalTime punchOutTime = (LocalTime) result[1];
                Duration duration = Duration.between(punchInTime, punchOutTime);
                totalPunchHours += duration.toHours();
                totalPunchMinutes += duration.toMinutesPart();
                totalPunchSeconds += duration.toSecondsPart();
            }
        }

        totalPunchMinutes += totalPunchSeconds / 60;
        totalPunchMinutes = totalPunchMinutes % 60;
        totalPunchSeconds = totalPunchSeconds % 60;
        totalPunchHours += totalPunchMinutes / 60; 

//        return String.format("%d : %d : %d ", totalPunchHours, totalPunchMinutes, totalPunchSeconds);
        return String.format("%d", totalPunchHours);
	}
	
	@Override
	public AttendanceCount getAttendanceCountByDate(LocalDate date, String username) {
		TypedQuery<AttendanceCount> query = entityManager.createQuery("SELECT ac FROM AttendanceCount ac WHERE ac.date =:date AND ac.name =:username", AttendanceCount.class);
        query.setParameter("date", date);
        query.setParameter("username", username);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
	}

//	--------------------- Department List -----------------------------
	@Override
	public List<Department> findDepartmentList() {
		
		TypedQuery<Department> theQuery = entityManager.createQuery("FROM Department", Department.class);
		List<Department> departmentList = theQuery.getResultList();
		return departmentList;
	}

	@Override
	public Department findeDepartmentById(int theId) {
		
		Department findDepartmentById = entityManager.find(Department.class, theId);
		return findDepartmentById;
	}

	@Override
	public Department saveDepartmentList(Department department) {
		
		Department saveDepartmentList = entityManager.merge(department);
		return saveDepartmentList;
	}

	@Override
	public void deleteDepartmentById(int theId) {

		Department deleteDepartmentById = entityManager.find(Department.class, theId);
		entityManager.remove(deleteDepartmentById);
	}















}
