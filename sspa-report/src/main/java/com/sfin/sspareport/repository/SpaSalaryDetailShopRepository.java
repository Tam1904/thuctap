package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.SpaSalaryDetailShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpaSalaryDetailShopRepository extends JpaRepository<SpaSalaryDetailShop, Void>, JpaSpecificationExecutor<SpaSalaryDetailShop> {

}