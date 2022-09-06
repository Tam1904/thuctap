package com.example.projects.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL)
    private List<Personnel> personnelList;
}
