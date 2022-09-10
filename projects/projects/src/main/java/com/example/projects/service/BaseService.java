package com.example.projects.service;

import com.example.projects.entity.Employee;

import java.util.List;

public interface BaseService {
    List<Employee> getByCity(Integer city_id);

    List<Employee> getByDepartment(Integer id);

    List<Employee> getPage(Integer index,Integer size);

}
