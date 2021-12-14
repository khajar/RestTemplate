package com.example.springresttemplateserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springresttemplateserver.Exception.ResourceNotFoundException;
import com.example.springresttemplateserver.model.Employee;
import com.example.springresttemplateserver.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployees()
	{
		List<Employee> emp= employeeRepository.findAll();
		if(emp.isEmpty())
		{
			throw new ResourceNotFoundException("no data found");
		}else
		{
			return emp;
		}
	}
	
	public void deleteEmployee(int id)
	{
		employeeRepository.deleteById(id);
		
	}
	
	public Optional<Employee> getEmployee(int id)
	{
		return employeeRepository.findById(id);
	}
	
			
}
