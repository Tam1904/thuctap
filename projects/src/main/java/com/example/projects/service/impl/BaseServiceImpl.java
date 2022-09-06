package com.example.projects.service.impl;

import com.example.projects.repository.PersonnelRepository;
import com.example.projects.service.BaseService;
import com.example.projects.entity.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {

    private final PersonnelRepository personnelRepository;

    @Autowired
    public BaseServiceImpl(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @Override
    public List<Personnel> getByCity(Integer city_id) {
        return personnelRepository.findByCityId(city_id);
    }

    @Override
    public List<Personnel> getByDepartment(Integer id) {
        return personnelRepository.findByDepartmentId(id);
    }


}
