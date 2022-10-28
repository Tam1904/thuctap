package com.sfin.sspareport.repository;

import com.sfin.sspareport.entity.SpaOrderSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpaOrderScheduleRepository extends JpaRepository<SpaOrderSchedule, Long>, JpaSpecificationExecutor<SpaOrderSchedule> {
    @Query(value = "select count(*) from POD_01.SPA_ORDER_SCHEDULE o where o.created_date >= :date1 and o.created_date < :date2",nativeQuery = true)
    Long countByCreatedDateBetween(@Param("date1")String date1, @Param("date2")String date2);

    @Query(value = "select sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o , POD_01.SHOP_PRODUCTS s where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and o.shop_id = s.shop_id",nativeQuery = true)
    Long totalMoney(@Param("date1") String date1, @Param("date2") String date2);

    @Query(value = "select count(*), sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o , POD_01.SHOP_PRODUCTS s where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and o.shop_id = s.shop_id",nativeQuery = true)
    String getCountAndTotalMoney(@Param("date1") String date1, @Param("date2") String date2);

    @Query(value = "select s.shop_id,  sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and s.shop_id = o.shop_id  group by o.shop_id",nativeQuery = true)
    List<String> totalMoneyGroupByShop(@Param("date1") String date1, @Param("date2") String date2);

    @Query(value = "select s.shop_id, ss.shop_name, sum(s.price) from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s , SSHOP_MANAGEMENT.SHOP_PROFILE ss where o.product_id = s.product_id and o.created_date >= :date1 and o.created_date < :date2 and s.shop_id = o.shop_id and ss.shop_id = o.shop_id group by o.shop_id",nativeQuery = true)
    List<String> totalMoneyGroupByShopId(@Param("date1") String date1, @Param("date2") String date2);

    @Query(value = "select * from POD_01.SPA_ORDER_SCHEDULE o where o.created_date >= :date1 and o.createdDate < :date2 and o.shop_id = :shopId",nativeQuery = true)
    List<SpaOrderSchedule> getOrderByShopId(@Param("date1")String date1, @Param("date2")String date2, @Param("shopId") Long shopId);

    @Query(value = "select o.order_id, o.customer_name, DATE(o.created_date), sum(s.price)  from POD_01.SPA_ORDER_SCHEDULE o, POD_01.SHOP_PRODUCTS s where o.created_date >= :date1 and o.created_date < :date2 and o.shop_id = :shopId and o.shop_id = s.shop_id and o.product_name = s.product_name group by o.order_id ,o.customer_name, DATE(o.created_date)",nativeQuery = true)
    List<String> getOrderByShopIdAndOderId(@Param("date1")String date1, @Param("date2")String date2, @Param("shopId") Long shopId);

}