package com.sfin.sspareport.repository;

import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.entity.ShopProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Repository
public interface ShopProductsRepository extends JpaRepository<ShopProducts, Long>, JpaSpecificationExecutor<ShopProducts> {

    // lấy tổng số sản phẩm được tạo ra giữa 2 ngày -> OK
    @Query(value = "select count(s.product_id) from POD_01.SHOP_PRODUCTS s left join POD_01.SPA_SALARY_DETAIL_PRODUCT spa on s.product_id = spa.product_id  where s.created_date >= :date and s.created_date <= :date2", nativeQuery = true)
    Long findAllByCreatedDate(@Param("date") String date, @Param("date2") String date2);

    // thông tin các shop và số lượng sản phẩm tạo ra
    @Query(value = "select s.shop_id, ss.shop_name, count(s.product_id), ss.shop_phone,concat(ss.ward_name,', ', ss.district_name, ', ', ss.province_name )   from POD_01.SHOP_PRODUCTS s , POD_01.SPA_SALARY_DETAIL_PRODUCT spa, SSHOP_MANAGEMENT.SHOP_PROFILE ss where s.product_id = spa.product_id and s.created_date >= :date1 and s.created_date < :date2 and ss.shop_id = s.shop_id group by s.shop_id"
            , countQuery = "select s.shop_id, ss.shop_name, count(*), ss.shop_phone,concat(ss.ward_name,', ', ss.district_name, ', ', ss.province_name )   from POD_01.SHOP_PRODUCTS s , POD_01.SPA_SALARY_DETAIL_PRODUCT spa, SSHOP_MANAGEMENT.SHOP_PROFILE ss where s.product_id = spa.product_id and s.created_date >= :date1 and s.created_date < :date2 and ss.shop_id = s.shop_id group by s.shop_id",nativeQuery = true)
    Page<Tuple> findByShopAndDate(@Param("date1") String date, @Param("date2") String date2,Pageable pageable);

    // danh sách sản phẩm cửa shop đó
    @Query(value = "select * from POD_01.SHOP_PRODUCTS s where s.created_date >= :date1 and s.created_date < :date2 and s.shop_id = :shopId",
            countQuery = "select * from POD_01.SHOP_PRODUCTS s where s.created_date >= :date1 and s.created_date < :date2 and s.shop_id = :shopId", nativeQuery = true)
    Page<ShopProducts> findProductsByShopAndDate(@Param("date1") String date1, @Param("date2") String date2, @Param("shopId") Long shopId, Pageable pageable);

}
