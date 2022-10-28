package com.sfin.sspareport.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "SPA_SALARY_DETAIL_PRODUCT", schema = "POD_01", catalog = "POD_01")
public class SpaSalaryDetailProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "commission_percentage")
    private Float commissionPercentage;

    /**
     * cấu hình thù lao theo buổi (số tiền cố định)
     */
    @Column(name = "remuneration")
    private Long remuneration;

    @Column(name = "created_date")
    private Date createdDate;

    /**
     * cấu hình thù lao theo buổi (theo % dịch vụ)
     */
    @Column(name = "remuneration_percentage")
    private Float remunerationPercentage;

    /**
     * Cấu hình thanh toán nhiều kỹ thuật viên 0 - trả chung 1 - trả riêng
     */
    @Column(name = "divide_method")
    private Integer divideMethod;

}
