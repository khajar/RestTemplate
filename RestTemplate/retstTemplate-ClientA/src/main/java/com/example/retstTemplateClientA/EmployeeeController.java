package com.example.retstTemplateClientA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class EmployeeeController {

    @Autowired
    private RestTemplate restTemplate;
    

    @GetMapping("/empList")
    List<Employee> getUserList(){
    	
        return restTemplate.getForObject("/Employees",List.class);
    	
    }
}
