package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.ChainProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChainProfileRepository extends JpaRepository<ChainProfile, Long>, JpaSpecificationExecutor<ChainProfile> {

    @Query(value = "select sh.chain_id, c.name, c.sub_domain, DATE(sh.created_date) from (select s.chain_id, min(s.created_date) as created_date from SSHOP_MANAGEMENT.SHOP_PROFILE s group by s.chain_id) as sh , SSHOP_MANAGEMENT.CHAIN_PROFILE c  where sh.created_date >= :date1 and sh.created_date < :date2 and c.chain_id = sh.chain_id",nativeQuery = true)
    List<ChainProfile> getChain(@Param("date1")String date1, @Param("date2")String date2);

}