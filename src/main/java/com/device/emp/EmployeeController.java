package com.device.emp;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String viewLoginPage() {
		return "login";
	}

	@GetMapping("/deviceRepositoryHome")
	public String viewHomePage() {
		return "home";
	}

	@GetMapping("/employeeRegisterForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("employe", new Employee());
		return "employee_registration";
	}

	@PostMapping("/employeeRegisterForm")
	public String processRegister(Employee employe) {
		Employee employee;
		try {
			employee = employeeService.save(employe);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return "duplicate_employee";
		}
		return "success_message";
	}

	@GetMapping("/employeerequest")
	public String RequestingPage() {
		return "Userrequesting";
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/emplist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getListEmployees() {
		JSONArray empJsonList = new JSONArray();
		List<Employee> empList = employeeService.getAllEmps();
		for (Employee employe : empList) {
			JSONObject obj = new JSONObject();
			obj.put("emp_id", employe.getEmpid());
			obj.put("emp_name", employe.getFirstName() + " " + employe.getLastName());
			empJsonList.add(obj);
		}
		return new ResponseEntity<Object>(empJsonList.toJSONString(), HttpStatus.OK);
	}
}