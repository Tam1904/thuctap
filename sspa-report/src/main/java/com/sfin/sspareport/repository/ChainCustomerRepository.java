package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.ChainCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChainCustomerRepository extends JpaRepository<ChainCustomer, Long>, JpaSpecificationExecutor<ChainCustomer> {

}