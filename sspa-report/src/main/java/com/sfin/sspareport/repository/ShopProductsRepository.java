package com.sfin.sspareport.repository;

import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShopProductsRepository extends JpaRepository<ShopProducts, Long>, JpaSpecificationExecutor<ShopProducts> {

    @Query(value = "select count(*) from POD_01.SHOP_PRODUCTS s ,POD_01.SPA_SALARY_DETAIL_PRODUCT spa where s.created_date >= :date and s.created_date <= :date2 and s.product_id = spa.product_id", nativeQuery = true)
    Long findAllByCreatedDate(@Param("date") String date, @Param("date2") String date2);

    @Query(value = "select s.shop_id, s.shop_name, count(*) from POD_01.SHOP_PRODUCTS s inner join POD_01.SPA_SALARY_DETAIL_PRODUCT spa on s.product_id = spa.product_id where s.created_date >= :date1 and s.created_date < :date2 group by s.shop_id", nativeQuery = true)
    List<String> findByShopIdAndDate(@Param("date1") String date, @Param("date2") String date2);

    @Query(value = "select s.shop_id, ss.shop_name, count(*) from POD_01.SHOP_PRODUCTS s , POD_01.SPA_SALARY_DETAIL_PRODUCT spa, SSHOP_MANAGEMENT.SHOP_PROFILE ss where s.product_id = spa.product_id and s.created_date >= :date1 and s.created_date < :date2 and ss.shop_id = s.shop_id group by s.shop_id", nativeQuery = true)
    List<String> findByShopAndDate(@Param("date1") String date, @Param("date2") String date2);

    @Query(value = "select * from POD_01.SHOP_PRODUCTS s where s.created_date >= :date1 and s.created_date < :date2 and s.shop_id = :shopId", nativeQuery = true)
    List<ShopProducts> findShopProductsByShopIdAndDate(@Param("date1") String date, @Param("date2") String date2, @Param("shopId") Long shopId);

    @Query(value = "select * from POD_01.SHOP_PRODUCTS s where s.created_date >= :date1 and s.created_date < :date2 and s.shop_id = :shopId", nativeQuery = true)
    Page<ShopProducts> findProductsByShopAndDate(@Param("date1") String date1, @Param("date2") String date2, @Param("shopId") Long shopId, Pageable pageable);


}
