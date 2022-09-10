package com.example.demo.entities;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("department")
public class Department implements Serializable {

    private Integer id;

    private  String name;

}
