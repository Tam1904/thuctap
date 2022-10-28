package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "SPA_ORDER_SCHEDULE", schema = "POD_01", catalog = "POD_01")
public class SpaOrderSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "schedule_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "shop_id")
    private Long shopId;

    /**
     * Ngày đặt lịch(khách hàng có thể sửa đổi)
     */
    @Column(name = "schedule_date")
    private String scheduleDate;

    @Column(name = "schedule_end")
    private String scheduleEnd;

    @Column(name = "schedule_date_1")
    private Date scheduleDate1;

    @Column(name = "schedule_end_1")
    private Date scheduleEnd1;

    /**
     * Kỹ thuật viên
     */
    @Column(name = "technical_staff_id")
    private String technicalStaffId;

    /**
     * Nhân viên sale
     */
    @Column(name = "sale_staff_id")
    private String saleStaffId;

    @Column(name = "note")
    @Type(type = "text")
    private String note;

    /**
     * status: 0: Lịch chưa sử dụng
     * 1: Lịch đã sử dụng(Checkout)
     * 2: Khách hàng không đến
     * 3: Khách hàng sửa lịch
     * 4: Lịch đã bị huỷ
     * 5: Indoing - Khách hàng đang sử dụng dịch vụ(Checkin)
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "checkin_date")
    private Date checkinDate;

    @Column(name = "checkout_date")
    private Date checkoutDate;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "auto_start")
    private Boolean autoStart;

    @Column(name = "auto_stop")
    private Boolean autoStop;

}
