package com.example.projects.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name="city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private List<District> districtList;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Employee> personnelCityList;
}
