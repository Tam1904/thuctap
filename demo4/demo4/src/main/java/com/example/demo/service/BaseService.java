package com.example.demo.service;

import com.example.demo.entities.Employee;

import java.util.List;

public interface BaseService {

    void saveEmployee(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    List<Employee> getEmployeesByCityId(Integer id);

    List<Employee> getEmployeesByDepartmentId(Integer id);


    void deleteEmployee(Integer id);
}
