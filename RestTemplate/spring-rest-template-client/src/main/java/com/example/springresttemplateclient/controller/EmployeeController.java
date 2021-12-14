package com.example.springresttemplateclient.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.springresttemplateclient.Exception.ResourceNotFoundException;
import com.example.springresttemplateclient.Service.EmployeeService;
import com.example.springresttemplateclient.Service.RestTemplateResponseErrorHandler;
import com.example.springresttemplateclient.model.Employee;

@RestController
@RequestMapping("/client-employee")
public class EmployeeController {

	private Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private RestTemplate restTemplate;
	

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmp(employee);
	}

	@PostMapping("/employeee")
	public Employee saveEmp(@RequestBody Employee emp) {
//		try {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<>(emp, httpHeaders);
		return restTemplate.exchange("http://localhost:8080/employee", HttpMethod.POST, entity, Employee.class)
				.getBody();
//		}catch(Exception e) {
//			System.out.println("exception is ::"+e);
//		}
//		return emp;
}

	@GetMapping("/empEntity")
	private void getListObject() {
		
//		try {
		ResponseEntity<List> emp = restTemplate.getForEntity("http://localhost:8080/Employees", List.class);
		HttpStatus statusCode = emp.getStatusCode();

		log.info("status code -" + statusCode);
		List<Object> empList = emp.getBody();

		log.info("response body - " + empList);
		HttpHeaders responseHeaders = emp.getHeaders();

		log.info("response Headers - " + responseHeaders);
//		}catch(ResourceNotFoundException e)
//		{
//			log.error("exceprion:"+e);
//		}
		
		
		
	}

	@GetMapping("/employees")
	public List<Employee> getAll() {
		List<Employee> emp= employeeService.getEmployee();
		if(emp.isEmpty())
		{
			throw new ResourceNotFoundException("no data found");
		}
		else
		{
			return emp;
		}
	}

	@GetMapping("/Employees")
	public Object getEmploees() {
		return employeeService.getEmployees();
	}

	@PostMapping("/postEmployee")
	public Employee postObj(@RequestBody Employee employee) {
		return employeeService.postObject(employee);
	}

	@GetMapping("/Employees/entity")
	public Object getEmploeesEntity() {
		return employeeService.getEmployeeEntity();
	}
	
	@DeleteMapping("/Employees/{id}")
	public Object deleteEmployee(@PathVariable int id)throws ResourceNotFoundException
	{

		Object emp=employeeService.deleteEmployee(id);
		if(emp==null)
		{
			throw new ResourceNotFoundException("no date found");
		}
		else return emp;
	}
	
	@GetMapping("/Employees/{id}")
	public Employee getEmployeeById(@PathVariable int id)
	{
		return employeeService.getEmployeeById(id);
	}

}
