package com.example.projects.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonnelDTO {
    private Integer id;
    private String name;
    private Long phone;
    private Date date;
    private String address;


}
