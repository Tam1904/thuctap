package com.example.projects.repository;

import com.example.projects.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    Optional<City> findById(Integer id);
}
