package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    @Id
    private String username;

    private String token;
}
