package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "SPA_SALARY_DETAIL_SHOP", schema = "POD_01", catalog = "POD_01")
public class SpaSalaryDetailShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "commission_percentage")
    private Float commissionPercentage;

    @Column(name = "remuneration")
    private Long remuneration;

    @Column(name = "function")
    @Type(type = "text")
    private String function;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

}
