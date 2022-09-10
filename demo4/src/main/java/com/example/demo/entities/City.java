package com.example.demo.entities;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("city")
public class City implements Serializable {

    private Integer id;

    private String name;
}
