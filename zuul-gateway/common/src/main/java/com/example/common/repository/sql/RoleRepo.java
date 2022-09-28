package com.example.common.repository.sql;

import com.example.common.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.id.user.id = :userId and r.active = true")
    HashSet<Role> findRoleByAppId(@Param("userId") int userId);
}

