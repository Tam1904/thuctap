package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.ChainProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChainProfileRepository extends JpaRepository<ChainProfile, Long>, JpaSpecificationExecutor<ChainProfile> {

}