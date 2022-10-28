package com.sfin.sspareport.service;

import com.sfin.sspareport.entity.ChainCustomer;
import com.sfin.sspareport.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private ChainCustomerRepository cusRepository;

//    @Autowired
//    private ChainEmploymentProfileRepository emRepository;

    @Autowired
    private SpaOrderScheduleRepository orderRepository;

    @Autowired
    private SpaSalaryDetailProductRepository proRepository;

    @Autowired
    private SpaSalaryDetailShopRepository shopRepository;

    public List<ChainCustomer> getAll(){
        return cusRepository.findAll();
    }



}
