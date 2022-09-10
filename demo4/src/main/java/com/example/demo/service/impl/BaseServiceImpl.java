package com.example.demo.service.impl;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    private CityRepository cityRepository;
    private DepartmentRepository departmentRepository;
    private DistrictRepository districtRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public BaseServiceImpl (CityRepository cityRepository, DepartmentRepository departmentRepository, DistrictRepository districtRepository, EmployeeRepository employeeRepository){
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByCityId(Integer id) {
        return employeeRepository.findByCity(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer id) {
        return null;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
