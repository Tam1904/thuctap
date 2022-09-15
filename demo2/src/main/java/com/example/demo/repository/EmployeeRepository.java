package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "select * from (select *,row_number() over (order by name) as row_num from employee) " +
            "as employee where row_num between (?1-1)*6 and (?1)*6-1 ;",nativeQuery = true)
    List<Employee> findEmployees(Integer index);
}
