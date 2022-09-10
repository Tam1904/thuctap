package com.example.projects.service.impl;

import com.example.projects.entity.Employee;
import com.example.projects.repository.CityRepository;
import com.example.projects.repository.DepartRepository;
import com.example.projects.repository.PersonnelRepository;
import com.example.projects.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    private final PersonnelRepository personnelRepository;
    private final CityRepository cityRepository;

    private  final DepartRepository departRepository;

    @Autowired
    public BaseServiceImpl(PersonnelRepository personnelRepository, CityRepository cityRepository,
                           DepartRepository departRepository) {
        this.cityRepository = cityRepository;
        this.departRepository = departRepository;
        this.personnelRepository = personnelRepository;
    }

    @Override
    public List<Employee> getByCity(Integer city_id) {
        return cityRepository.findById(city_id).get().getPersonnelCityList();
    }

    @Override
    public List<Employee> getByDepartment(Integer id) {

        return departRepository.findById(id).get().getDePersonnelList();
    }

    @Override
    public List<Employee> getPage(Integer index, Integer size) {
        return personnelRepository.findByIndex(index,size);
    }


}
