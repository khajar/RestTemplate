package com.example.springresttemplateserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springresttemplateserver.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
