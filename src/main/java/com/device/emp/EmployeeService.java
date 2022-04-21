package com.device.emp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/*
	 * TODO: Get the List of Employees
	 */
	public List<Employee> getAllEmps() {
		List<Employee> list = employeeRepository.findAll();
		return list;
	}

	/*
	 * TODO: Get Employee By keyword
	 */
	public List<Employee> getByKeyword(String keyword) {
		return employeeRepository.findByKeyword(keyword);
	}

	public Employee save(Employee employe) {

		return employeeRepository.save(employe);
	}
}