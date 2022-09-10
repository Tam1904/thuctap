package com.example.projects.repository;

import com.example.projects.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartRepository extends JpaRepository<Department,Integer> {

}
