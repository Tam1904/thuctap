package com.example.common.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "application_api_mascom", schema = "public")
@ToString
public class Role {


    @EmbeddedId
    protected RolePK id;

    @Column(name = "role")
    protected String role;

    @Column(name = "active")
    protected boolean active;
}
