package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.SpaOrderSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Repository
public interface SpaOrderScheduleRepository extends JpaRepository<SpaOrderSchedule, Long>, JpaSpecificationExecutor<SpaOrderSchedule> {

    // trả về số lượng đơn hàng và tổng tiền -> cần lưu ý
    @Query(value = "select count(o.shop_id), sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o , POD_01.SHOP_PRODUCTS s where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and o.shop_id = s.shop_id ",nativeQuery = true)
    String getCountAndTotalMoney(@Param("date1") String date1, @Param("date2") String date2);

    // lấy danh sách các shop và tổng tiền là bao nhiêu
    @Query(value = "select s.shop_id, ss.shop_name, ss.shop_phone, concat(ss.ward_name,', ', ss.district_name, ', ', ss.province_name ), sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s , SSHOP_MANAGEMENT.SHOP_PROFILE ss where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and s.shop_id = o.shop_id and ss.shop_id = o.shop_id group by o.shop_id",
            countQuery = "select s.shop_id, ss.shop_name, sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s , SSHOP_MANAGEMENT.SHOP_PROFILE ss where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and s.shop_id = o.shop_id and ss.shop_id = o.shop_id group by o.shop_id",nativeQuery = true) // 1 product thuoc 1 shop
    Page<Tuple> totalMoneyGroupByShopId(@Param("date1") String date1, @Param("date2") String date2, Pageable pageable);

    // order của khách hàng nào tổng giá trị nhiêu
    @Query(value = "select o.order_id, o.customer_id, o.customer_name, o.created_date, sum(s.price)  from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s where o.created_date >= :date1 and o.created_date < :date2 and o.shop_id = :shopId and o.shop_id = s.shop_id and o.product_id = s.product_id group by o.order_id ,o.customer_name, DATE(o.created_date)",
           countQuery = "select o.order_id, o.customer_id,o.customer_name, o.created_date, sum(s.price)  from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s where o.created_date >= :date1 and o.created_date < :date2 and o.shop_id = :shopId and o.shop_id = s.shop_id and o.product_id = s.product_id group by o.order_id ,o.customer_name, DATE(o.created_date)",nativeQuery = true)
    Page<Tuple> getOrderByShopIdAndOderId(@Param("date1")String date1, @Param("date2")String date2, @Param("shopId") Long shopId, Pageable pageable);

}