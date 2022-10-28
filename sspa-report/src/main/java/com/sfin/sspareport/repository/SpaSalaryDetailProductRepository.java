package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.entity.SpaSalaryDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaSalaryDetailProductRepository extends JpaRepository<SpaSalaryDetailProduct, Long>, JpaSpecificationExecutor<SpaSalaryDetailProduct> {

    @Query(value = "select count(*) from SPA_SALARY_DETAIL_PRODUCT s where s.created_date >= :date and s.created_date <= :date2",nativeQuery = true)
    Long findAllByCreatedDate(@Param("date") String date, @Param("date2")String date2);

    @Query(value = "select s.shop_id, s.shop_name, count(*) from SPA_SALARY_DETAIL_PRODUCT s where s.created_date >= :date1 and s.created_date < :date2 group by s.shop_id",nativeQuery = true)
    List<String> findByShopIdAndDate(@Param("date1") String date, @Param("date2")String date2);

    @Query(value = "select * from SPA_SALARY_DETAIL_PRODUCT s where s.created_date >= :date1 and s.created_date < :date2 and s.shop_id = :shopId",nativeQuery = true)
    List<SpaSalaryDetailProduct> findShopProductsByShopIdAndDate(@Param("date1") String date, @Param("date2")String date2, @Param("shopId")Long shopId);

}