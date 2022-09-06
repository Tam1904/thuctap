package com.example.projects.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private String name;

    private Long phone;

    @ManyToOne
    private Department department;

    @ManyToOne
    private City city;

    @ManyToOne
    private District district;

}
