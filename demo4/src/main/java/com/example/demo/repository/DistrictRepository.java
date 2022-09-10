package com.example.demo.repository;

import com.example.demo.entities.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District,Integer> {
}
