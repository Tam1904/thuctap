package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ShopReportRepository extends JpaRepository<ShopReport, Long>, JpaSpecificationExecutor<ShopReport> {

    @Query(value = "select MIN (DATE(s.created_date)) from SSHOP_MANAGEMENT.SHOP_PROFILE s, SSHOP_MANAGEMENT.CHAIN_PROFILE c where s.chain_id = c.chain_id ",nativeQuery = true)
    Date getMinDate();

    @Query(value = "select concat(MONTH (:date),'-' , YEAR (:date) ), sum(amount_chain), sum(amount_shop), sum(amount_product), sum(amount_order), sum(total_money) from SSHOP_REPORTING.SHOP_REPORT sr where MONTH(sr.report_date) = MONTH (:date) and YEAR(sr.report_date) = YEAR(:date)", nativeQuery = true)
    String getTotalReport(@Param("date") Date date);

}