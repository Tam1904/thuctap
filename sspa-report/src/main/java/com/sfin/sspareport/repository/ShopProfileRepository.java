package com.sfin.sspareport.repository;

import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface ShopProfileRepository extends JpaRepository<ShopProfile, Long>, JpaSpecificationExecutor<ShopProfile> {

    @Query(value = "select * from SSHOP_MANAGEMENT.SHOP_PROFILE s where s.created_date >= :date1 and s.created_date < :date2",nativeQuery = true)
    List<ShopProfile> getShopProfilesByCreatedDate(@Param("date1")String date1, @Param("date2")String date2);

    @Query(value = "select sh.chain_id, c.name, c.sub_domain, sh.created_date from (select s.chain_id, min(s.created_date) as created_date from SSHOP_MANAGEMENT.SHOP_PROFILE s group by s.chain_id) as sh , SSHOP_MANAGEMENT.CHAIN_PROFILE c  where sh.created_date >= :date1 and sh.created_date < :date2 and c.chain_id = sh.chain_id",
            countQuery = "select sh.chain_id, c.name, c.sub_domain, DATE(sh.created_date) from (select s.chain_id, min(s.created_date) as created_date from SSHOP_MANAGEMENT.SHOP_PROFILE s group by s.chain_id) as sh , SSHOP_MANAGEMENT.CHAIN_PROFILE c  where sh.created_date >= :date1 and sh.created_date < :date2 and c.chain_id = sh.chain_id",nativeQuery = true)
    Page<Tuple> getChain(@Param("date1")String date1, @Param("date2")String date2,Pageable pageable);

//    @Query(value = "select s.shop_id as shop_id, ss.commission_percentage as commission_percentage, ss.remuneration as remuneration , s.shop_name as shop_name, s.shop_phone as shop_phone  from SSHOP_MANAGEMENT.SHOP_PROFILE s left join POD_01.SPA_SALARY_DETAIL_SHOP ss on ss.shop_id = s.shop_id where s.chain_id = :chainId and s.created_date >= :date1 and s.created_date < :date2",nativeQuery = true)
//    List<Tuple> getShopByChainId(@Param("chainId") Long chainId, @Param("date1")String date1, @Param("date2")String date2);
    @Query(value = "select s.shop_id as shop_id, ss.commission_percentage as commission_percentage, ss.remuneration as remuneration, s.shop_name , s.shop_phone , s.ward_name,s .district_name, s.province_name from SSHOP_MANAGEMENT.SHOP_PROFILE s left join POD_01.SPA_SALARY_DETAIL_SHOP ss on ss.shop_id = s.shop_id where s.chain_id = :chainId and s.created_date >= :date1 and s.created_date < :date2",
            countQuery = "select s.shop_id as shop_id, ss.commission_percentage as commission_percentage, ss.remuneration as remuneration, s.shop_name as shop_name from SSHOP_MANAGEMENT.SHOP_PROFILE s left join POD_01.SPA_SALARY_DETAIL_SHOP ss on ss.shop_id = s.shop_id where s.chain_id = :chainId and s.created_date >= :date1 and s.created_date < :date2"
            ,nativeQuery = true)
    Page<Tuple> getShopByChainId(@Param("chainId") Long chainId, @Param("date1")String date1, @Param("date2")String date2,Pageable pageable);

    @Query(value = "select s.shop_id as shop_id, ss.commission_percentage, ss.remuneration, s.shop_name, s.shop_phone, s.ward_name, s.district_name, s.province_name  from SSHOP_MANAGEMENT.SHOP_PROFILE s left join POD_01.SPA_SALARY_DETAIL_SHOP ss on ss.shop_id = s.shop_id where s.chain_id = :chainId and s.created_date >= :date1 and s.created_date < :date2"
            ,countQuery = "select s.shop_id as shop_id, ss.commission_percentage, ss.remuneration, s.shop_name, s.shop_phone, s.ward_name, s.district_name, s.province_name  from SSHOP_MANAGEMENT.SHOP_PROFILE s left join POD_01.SPA_SALARY_DETAIL_SHOP ss on ss.shop_id = s.shop_id where s.chain_id = :chainId and s.created_date >= :date1 and s.created_date < :date2"
            ,nativeQuery = true)
    Page<String> getShopByChainIdV1(@Param("chainId") Long chainId, @Param("date1")String date1, @Param("date2")String date2, Pageable pageable);

}