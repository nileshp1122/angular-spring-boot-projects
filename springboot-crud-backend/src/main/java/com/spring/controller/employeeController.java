package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.employeeDao;
import com.spring.exception.ResourceNotFoundException;
import com.spring.model.Employee;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class employeeController {
	
	@Autowired employeeDao employeeDao;
	
	// get all employees
	@GetMapping("/employees")
	public  List<Employee> getAllEmployees() {
		return employeeDao.findAll();
	}
	
	// add employee data
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeDao.save(employee);
	}
	
	// get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		return ResponseEntity.ok(employee);
	}
	
	// update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetail) {
		Employee employee = employeeDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		employee.setFirstName(employeeDetail.getFirstName());
		employee.setLastName(employeeDetail.getLastName());
		employee.setEmailId(employeeDetail.getEmailId());
		
		Employee updatedEmployee = employeeDao.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		
		employeeDao.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
