package com.example.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.projects.entity.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonnelDTO {
    private Integer id;
    private String name;
    private Long phone;
    private Date date;
    private String address;

    public static PersonnelDTO convert(Employee employee){
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setId(employee.getId());
        personnelDTO.setName(employee.getName());
        personnelDTO.setPhone(employee.getPhone());
        personnelDTO.setDate(employee.getDate());
        personnelDTO.setAddress(employee.getDistrict().getName() + "," + employee.getCity().getName());
        return  personnelDTO;
    }

}
