package com.example.projects.repository;

import com.example.projects.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonnelRepository extends JpaRepository<Employee,Integer> {

    @Query(name = "select * from (select *,row_number() over (order by name) as row_num from department) " +
            " employee where row_num between (?1 * ?2) and ((?1+1) * ?2);",nativeQuery = true)
    List<Employee> findByIndex(Integer index,Integer size);
}
