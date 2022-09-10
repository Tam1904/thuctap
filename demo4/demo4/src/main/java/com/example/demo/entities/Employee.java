package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("employee")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee implements Serializable {

    private Integer id;

    private String name;

    private Long phone;

    private Date date;

    private Integer city;

    private Integer department;

    private Integer district;
}
