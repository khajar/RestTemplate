package com.example.springresttemplateserver.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springresttemplateserver.Exception.ResourceNotFoundException;
import com.example.springresttemplateserver.model.Employee;
import com.example.springresttemplateserver.repository.EmployeeRepository;
import com.example.springresttemplateserver.service.EmployeeService;

@RestController
public class EmployeeController
{
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee employee)
	{
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/Employees")
	public ResponseEntity<List<Employee>> getEmploees()
	{
		List<Employee> emp=  employeeService.getEmployees();
		
		if(emp == null)
		{
			throw new ResourceNotFoundException("no data found");
			
		}
		else
		{
			return new ResponseEntity<>(emp, HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable int id)
	{
		 employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
		
		employeeService.deleteEmployee(id);
		
		
	}
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployee(@PathVariable int id)
	{
		employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id to get details :" + id));
		return employeeService.getEmployee(id);
	}
	
		

}
