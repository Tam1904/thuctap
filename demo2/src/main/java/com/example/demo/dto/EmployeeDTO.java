package com.example.demo.dto;

import com.example.demo.entity.Employee;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {

    private Integer id;
    private String name;
    private Long phone;
    private Date date;
    private String address;

    public static EmployeeDTO convert(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setDate(employee.getDate());
        employeeDTO.setAddress(employee.getDistrict().getName() + "," + employee.getCity().getName());
        return employeeDTO;
    }
}
