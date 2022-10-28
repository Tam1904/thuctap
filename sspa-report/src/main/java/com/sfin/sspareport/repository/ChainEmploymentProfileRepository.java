package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.ChainEmploymentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChainEmploymentProfileRepository extends JpaRepository<ChainEmploymentProfile, Long>, JpaSpecificationExecutor<ChainEmploymentProfile> {

}