package com.example.demo.controller;


import java.time.LocalDate;



import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.entity.AttendanceCount;
import com.example.demo.entity.Department;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public String showEmployesPage() {
		return "/employees/employees";
	}

//---------------------  Employees List Controller   -----------------------------------------
		@GetMapping("/emp-list")
		 public String showEmpAllPage(Model theModel) {
			
			 List<Employee> employees = this.employeeService.findAll();
			 theModel.addAttribute("employees", employees);
			 return "/employees/emp-list";
		 }
		
		@GetMapping("/emp-add")
		public String empAddPage(Model theModel, HttpSession session) {
			
			String userName = (String) session.getAttribute("username");
			theModel.addAttribute("username",userName);
			
			Employee employees = new Employee();
			theModel.addAttribute("employees", employees);
			
			List<Department> department = this.employeeService.findDepartmentList();
			theModel.addAttribute("departments", department);
			return "/employees/emp-add";
		}

		@PostMapping("/emp-add")
		public String empAddPage(@Valid @ModelAttribute("employees") Employee employee, BindingResult theBindingResult, Model theModel, HttpSession session) {
			
			if(theBindingResult.hasErrors()) {
				String userName = (String) session.getAttribute("username");
				theModel.addAttribute("username",userName);
				List<Department> department = this.employeeService.findDepartmentList();
				theModel.addAttribute("departments", department);
				return "/employees/emp-add";
			}
			else {
				this.employeeService.save(employee);
				return "redirect:/emp-list";				
			}
		}
		
		@GetMapping("/emp-update")
		public String empUpdate(@RequestParam("employeeId") int theId, Model theModel, HttpSession session) {

			String userName = (String) session.getAttribute("username");
			
			List<Department> department = this.employeeService.findDepartmentList();
			Employee theEmployee = employeeService.findById(theId);
			
			theModel.addAttribute("departments", department);
			theModel.addAttribute("employees", theEmployee);
			theModel.addAttribute("username",userName);
			return "employees/emp-add";
		}
		
		@GetMapping("/emp-delete")
		public String empDelete(@RequestParam("employeeId") int theId) {
			
			this.employeeService.deleteById(theId);
			return "redirect:/emp-list";
		}
		
		@GetMapping("/emp-detail")
		public String empDetailPage(Model theModel) {
			
			List<Employee> employee = this.employeeService.findAll();
			theModel.addAttribute("employeesDetail",employee);
			return "/employees/emp-detail";
		}
		
//----------------------------  Leave Controller    ------------------------------------------------------
	@GetMapping("/emp-leave")
	public String showEmpLeaveAddPage(Model model) {	
		
		List<LeaveRequest> employees = this.employeeService.findLeaveList();
		model.addAttribute("employees",employees);
		return "/employees/emp-leave";
	}
	
    @PostMapping("/approve")
    public String approveRequest(@RequestParam("employeeId") int employeeId) {
        employeeService.approveLeaveRequest(employeeId);
        return "redirect:/emp-leave";
    }

    @PostMapping("/reject")
    public String rejectRequest(@RequestParam("employeeId") int employeeId) {
        employeeService.rejectLeaveRequest(employeeId);
        return "redirect:/emp-leave";
    }

	@GetMapping("/emp-leave-add")
	public String empLeaveAddPage(HttpSession session,Model theModel, LeaveRequest leaveRequest, Employee employee) {

		String userName = (String) session.getAttribute("username");
		theModel.addAttribute("username", userName);
		
	    String currentUserAuthority = (String) session.getAttribute("userAuthority");
	    String userAuthority = currentUserAuthority.substring(5);
	    theModel.addAttribute("userAuthority",userAuthority);
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		String currentUserId = this.employeeService.findByCurrentUserId(currentUser);
		
		theModel.addAttribute("employees", leaveRequest);
		theModel.addAttribute("currentUserId",currentUserId);
		return "/employees/emp-leave-add";
	}
	
	@PostMapping("/emp-leave-add")
	public String empLeaveAddPage(@Valid @ModelAttribute("employees") LeaveRequest employeeLeave, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/employees/emp-leave-add";
		}
		else {
			employeeLeave.setId(0);
			this.employeeService.saveLeaveRequest(employeeLeave);
			return "redirect:/emp-leave";
		}

	}
	
	@GetMapping("/emp-leave-delete")
	public String empLeaveDelete(@RequestParam("employeeId") int theId) {
		
		this.employeeService.deleteLeaveRequestById(theId);
		return "redirect:/emp-leave";
	}
	
	
//	-------------------------   Attendance Controller   -----------------------------------------------------------
	
	@GetMapping("/emp-attendance-count")
    public String showAttendancePage(HttpSession session, Model theModel) {
		
	    String currentUserAuthority = (String) session.getAttribute("userAuthority");
	    String userAuthority = currentUserAuthority.substring(5);
	    theModel.addAttribute("userAuthority",userAuthority);
		
		AttendanceCount attendanceCount = new AttendanceCount();
		LocalDate date = LocalDate.now();
		LocalDateTime dateTime = LocalDateTime.now();
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        
        List<AttendanceCount> employeeName = this.employeeService.findAttendanceByUsername(currentUser);
        long totalAttendance = this.employeeService.attendanceCount(currentUser);
        String totalHours = this.employeeService.attendanceHoursCount(currentUser);
        
        theModel.addAttribute("employees",employeeName);
        theModel.addAttribute("attendanceCount", totalAttendance);
        theModel.addAttribute("totalHours", totalHours);
        theModel.addAttribute("date",date);
        theModel.addAttribute("dateTime", dateTime);
        theModel.addAttribute("punchDateTime", attendanceCount);
        
        return "/employees/emp-attendance-count";
    }
	
	@PostMapping("/punchDateTime")
	public String punchInDateTime(@ModelAttribute("punchDateTime") AttendanceCount attendanceCount) {
		
		attendanceCount.setDate(LocalDate.now());
		attendanceCount.setPunchInTime(LocalTime.now());
		this.employeeService.saveAttendanceCount(attendanceCount);
		return "redirect:/emp-attendance-count";
	}
	
	@PostMapping("/punchOutTime")
	public String punchOutDateTime(@ModelAttribute("punchDateTime") AttendanceCount attendanceCount) {
		
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
	    AttendanceCount existingAttendance = this.employeeService.getAttendanceCountByDate(LocalDate.now(), currentUser);
	    existingAttendance.setPunchOutTime(LocalTime.now());
		this.employeeService.saveAttendanceCount(existingAttendance);
		return "redirect:/emp-attendance-count";
	}	
	
	
//	-------------------------    Employees Department Controller   -------------------------------------------------------
	@GetMapping("/emp-department")
	public String showEmpDepartmentPage(Model theModel) {
		
		List<Department> employeeDep = this.employeeService.findDepartmentList();
        List<Object[]> groupByRole = this.employeeService.groupByRole();
		
        theModel.addAttribute("employees",employeeDep);
		theModel.addAttribute("role",groupByRole);
		
		return "/employees/emp-department";
	}

	@GetMapping("/emp-department-add")
	public String empDepAddPage(Model theModel, Department department) {
		
        List<Employee> employees = this.employeeService.findAll();
         
        theModel.addAttribute("employeeList", employees);
        theModel.addAttribute("department", department);
		return "/employees/emp-department-add";
	}
	
	@PostMapping("/emp-department-add")
	public String empDepAddPage(@ModelAttribute("department") Department employeeDepartment, Model theModel) {
		
        List<Employee> employees = this.employeeService.findAll();
        
		this.employeeService.saveDepartmentList(employeeDepartment);
		theModel.addAttribute("employeeList", employees);
		return "redirect:/emp-department";
	}
	
	@GetMapping("/emp-department-update")
	public String empDepartmentUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
        List<Employee> employees = this.employeeService.findAll();
		Department theEmployee = this.employeeService.findeDepartmentById(theId);
		
		theModel.addAttribute("employeeList", employees);
		theModel.addAttribute("department",theEmployee);
		return "/employees/emp-department-add";
	}
	
	@GetMapping("/emp-department-delete")
	public String getMethodName(@RequestParam("employeeId") int theId) {
		
		this.employeeService.deleteDepartmentById(theId);
		return "redirect:/emp-department";
	}
	
	
}




