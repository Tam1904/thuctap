package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    private  CityRepository cityRepository;

    private  DepartmentRepository departmentRepository;

    private  EmployeeRepository employeeRepository;

    @Autowired
    public BaseServiceImpl(CityRepository cityRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.cityRepository = cityRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getByCity(Integer city_id) {
        return cityRepository.findById(city_id).get().getEmployees();
    }

    @Override
    public List<Employee> getByDepartment(Integer id) {

        return departmentRepository.findById(id).get().getEmployees();
    }

    @Override
    public List<Employee> getPage(Integer index) {
        return employeeRepository.findEmployees(index);
    }
}
