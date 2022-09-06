package com.example.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {
    List<Personnel> findByCityId(Integer id);

    List<Personnel> findByDepartmentId(Integer id);
}
