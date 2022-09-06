package com.example.projects.service;

import com.example.projects.entity.Personnel;

public interface BaseService {
    public List<Persionnel> getByCity(Integer city_id);

    public List<Personnel> getByDepartment(Integer id);


}
