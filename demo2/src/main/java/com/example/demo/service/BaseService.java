package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface BaseService {
    List<Employee> getByCity(Integer city_id);

    List<Employee> getByDepartment(Integer id);

    List<Employee> getPage(Integer index);

}
