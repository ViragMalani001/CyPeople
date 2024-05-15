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
import com.example.demo.Enum.EndPointEnum;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.AttendanceCount;
import com.example.demo.entity.Department;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;
	private EmployeeDAO employeeDAO;


	public EmployeeController(EmployeeService employeeService, EmployeeDAO employeeDAO) {
		super();
		this.employeeService = employeeService;
		this.employeeDAO = employeeDAO;
	}
	
//	Enum EndPoints
	String employeeURL = EndPointEnum.EMPLOYEES.getEndPoint();

//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	String currentUser = authentication.getName();
//	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	
	@GetMapping("/employees")
	public String showEmployesPage(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		return employeeURL;
	}

//---------------------  Employees List Controller   -----------------------------------------
	@GetMapping("/emp-list")
	public String showEmpAllPage(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		List<Employee> employeesList = this.employeeService.findAll();
		model.addAttribute("employeesList", employeesList);
//		System.out.println(employeesList);
		
		session.setAttribute("employeesList", employeesList);
		
		return employeeURL + "/emp-list";
	}

	@GetMapping("/emp-add")
	public String empAddPage(Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);

		Employee employees = new Employee();
		model.addAttribute("employees", employees);

		List<Department> department = this.employeeService.findDepartmentList();
		model.addAttribute("departments", department);
		
		String employeesList = (String) session.getAttribute("empoyeesList");
		model.addAttribute("employeesList",employeesList);
		
		return employeeURL + "/emp-add";
	}

	@PostMapping("/emp-add")
	public String empAddPage(@Valid @ModelAttribute("employees") Employee employee, BindingResult theBindingResult,
			Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {
			List<Department> department = this.employeeService.findDepartmentList();
			model.addAttribute("departments", department);
			return employeeURL + "/emp-add";
		} else {
			this.employeeService.save(employee);
			return "redirect:/emp-list";
		}
	}

	@GetMapping("/emp-update")
	public String empUpdate(@RequestParam("employeeId") int theId, Model model, HttpSession session) {
		
		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);

		Employee theEmployee = this.employeeService.findById(theId);
		List<Department> department = this.employeeService.findDepartmentList();

		model.addAttribute("employees", theEmployee);
		model.addAttribute("departments", department);
		return employeeURL + "/emp-add";
	}

	@GetMapping("/emp-delete")
	public String empDelete(@RequestParam("employeeId") int theId) {

		this.employeeService.deleteById(theId);
		return "redirect:/emp-list";
	}

	@GetMapping("/emp-detail")
	public String empDetailPage(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		List<Employee> employee = this.employeeService.findAll();
		model.addAttribute("employeesDetail", employee);
		return employeeURL + "/emp-detail";
	}

//----------------------------  Leave Controller    ------------------------------------------------------
	@GetMapping("/emp-leave")
	public String showEmpLeaveAddPage(Model model,LeaveRequest leaveRequest, HttpSession session) {

		try {
			String userName = (String) session.getAttribute("username");
			model.addAttribute("username",userName);
			
			String currentUserAuthority = (String) session.getAttribute("role");
			model.addAttribute("userAuthority",currentUserAuthority);
			
//			List<LeaveRequest> employees = this.employeeService.findLeaveList();
//			model.addAttribute("employees", employees);
			
			List<LeaveRequest> leaveRequests = this.employeeDAO.empNameNew();
			model.addAttribute("employees",leaveRequests);
			
			System.out.println("LeaveRequest");
			System.out.println(leaveRequests.toString());
			System.out.println("LeaveRequestEnd");
			
			return employeeURL + "/emp-leave";
			
		} catch (Exception e) {
			e.printStackTrace();
			return employeeURL + "/emp-leave";
		}

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
	public String empLeaveAddPage(HttpSession session, Model model, LeaveRequest leaveRequest, Employee employee) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentAuthority);
 		
		String currentUserId = this.employeeService.findByCurrentUserId(userName);

		model.addAttribute("employees", leaveRequest);
		model.addAttribute("currentUserId", currentUserId);
		return employeeURL + "/emp-leave-add";
	}

	@PostMapping("/emp-leave-add")
	public String empLeaveAddPage(@Valid @ModelAttribute("employees") LeaveRequest employeeLeave,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return employeeURL + "/emp-leave-add";
		} else {
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
	public String showAttendancePage(HttpSession session, Model model) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority", currentUserAuthority);

		AttendanceCount attendanceCount = new AttendanceCount();
		LocalDate date = LocalDate.now();
		LocalDateTime dateTime = LocalDateTime.now();
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		List<AttendanceCount> employeeName = this.employeeService.findAttendanceByUsername(currentUser);
		long totalAttendance = this.employeeService.attendanceCount(currentUser);
		String totalHours = this.employeeService.attendanceHoursCount(currentUser);

		model.addAttribute("employees", employeeName);
		model.addAttribute("attendanceCount", totalAttendance);
		model.addAttribute("totalHours", totalHours);
		model.addAttribute("date", date);
		model.addAttribute("dateTime", dateTime);
		model.addAttribute("punchDateTime", attendanceCount);

		return employeeURL + "/emp-attendance-count";
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
		AttendanceCount existingAttendance = this.employeeService.getAttendanceCountByDate(LocalDate.now(),
				currentUser);
		existingAttendance.setPunchOutTime(LocalTime.now());
		this.employeeService.saveAttendanceCount(existingAttendance);
		return "redirect:/emp-attendance-count";
	}

//	-------------------------    Employees Department Controller   -------------------------------------------------------
	@GetMapping("/emp-department")
	public String showEmpDepartmentPage(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("username",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		List<Department> employeeDep = this.employeeService.findDepartmentList();
		List<Object[]> groupByRole = this.employeeService.groupByRole();

		model.addAttribute("employees", employeeDep);
		model.addAttribute("role", groupByRole);

		return employeeURL + "/emp-department";
	}

	@GetMapping("/emp-department-add")
	public String empDepAddPage(Model model, Department department, HttpSession session) {

		String userName = (String) session.getAttribute("username");
		model.addAttribute("uername",userName);
		
		String currentUserAuthority = (String) session.getAttribute("role");
		model.addAttribute("userAuthority",currentUserAuthority);
		
		List<Employee> employees = this.employeeService.findAll();

		model.addAttribute("employeeList", employees);
		model.addAttribute("department", department);
		return employeeURL + "/emp-department-add";
	}

	@PostMapping("/emp-department-add")
	public String empDepAddPage(@ModelAttribute("department") Department employeeDepartment, Model model) {

		List<Employee> employees = this.employeeService.findAll();

		this.employeeService.saveDepartmentList(employeeDepartment);
		model.addAttribute("employeeList", employees);
		return "redirect:/emp-department";
	}

	@GetMapping("/emp-department-update")
	public String empDepartmentUpdate(@RequestParam("employeeId") int theId, Model model) {

		List<Employee> employees = this.employeeService.findAll();
		Department theEmployee = this.employeeService.findeDepartmentById(theId);

		model.addAttribute("employeeList", employees);
		model.addAttribute("department", theEmployee);
		return employeeURL + "/emp-department-add";
	}

	@GetMapping("/emp-department-delete")
	public String getMethodName(@RequestParam("employeeId") int theId) {

		this.employeeService.deleteDepartmentById(theId);
		return "redirect:/emp-department";
	}

}
