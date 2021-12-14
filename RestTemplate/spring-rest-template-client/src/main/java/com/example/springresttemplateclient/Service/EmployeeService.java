package com.example.springresttemplateclient.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springresttemplateclient.Exception.ResourceNotFoundException;
import com.example.springresttemplateclient.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
//	@Autowired
//	public EmployeeService(RestTemplateBuilder restTemplateBuilder ) {
//		this.restTemplate = restTemplateBuilder.build();
//	}

	public Employee saveEmp(Employee employee)
	{
		
		HttpEntity<Employee> entity = new HttpEntity<>(employee);
		
		return restTemplate.exchange("http://localhost:8080/employee", HttpMethod.POST, entity, Employee.class).getBody();
		
		
		
	}
	
	public List<Employee> getEmployee()throws ResourceNotFoundException
	{
//		List<Employee> emp = restTemplate.exchange("http://localhost:8080/Employees", HttpMethod.GET, null, List.class).getBody();
//		if(emp.isEmpty())
//		{
//			throw new ResourceNotFoundException("no data ");
//		}
//		else
//		{
//			return emp;
//		}
		return restTemplate.exchange("http://localhost:8080/Employees", HttpMethod.GET, null, List.class).getBody();
	}
	
	
	
	
	public Object getEmployees() {
		return this.restTemplate.getForObject("http://localhost:8080/Employees", List.class);
	}
	
	public Employee postObject(Employee employee) {
		return this.restTemplate.postForObject("http://localhost:8080/employee", employee, Employee.class);
	}
	public Object getEmployeeEntity() {
		return this.restTemplate.getForEntity("http://localhost:8080/Employees", List.class);
	}
	
	public Object deleteEmployee(int id)
	{
		 Map < String, Integer > params = new HashMap <String, Integer> ();
	        params.put("id",id );
	        restTemplate.delete("http://localhost:8080/employee/{id}", params);
			return restTemplate;
	}

	public Employee getEmployeeById(int id)
	{
		Map < String, Integer > params = new HashMap < String, Integer > ();
        params.put("id", id);
        Employee result = restTemplate.getForObject("http://localhost:8080/employee/{id}", Employee.class, params);
        return result;
        

	}
}
