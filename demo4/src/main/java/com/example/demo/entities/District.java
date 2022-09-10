package com.example.demo.entities;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("district")
public class District implements Serializable {
    private Integer id;

    private String name;

    private String city_id;
}
