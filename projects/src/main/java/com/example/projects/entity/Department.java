package com.example.projects.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//@Entity
@Table(name="department")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Employee> dePersonnelList;

}
