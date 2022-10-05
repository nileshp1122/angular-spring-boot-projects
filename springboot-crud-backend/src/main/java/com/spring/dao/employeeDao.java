package com.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Employee;

public interface employeeDao extends JpaRepository<Employee, Long> {

}
