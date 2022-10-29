package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "SHOP_PROFILE" , schema = "SSHOP_MANAGEMENT", catalog = "SSHOP_MANAGEMENT")
public class ShopProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "shop_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    /**
     * ID chuỗi
     */
    @Column(name = "chain_id")
    private Long chainId;


    @Column(name = "shop_code")
    private String shopCode;

    @Column(name = "shop_link")
    @Type(type = "text")
    private String shopLink;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "avatar")
    @Type(type = "text")
    private String avatar;

    @Column(name = "banner")
    @Type(type = "text")
    private String banner;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "shop_phone")
    private String shopPhone;

    @Column(name = "shop_lat")
    private Double shopLat;

    @Column(name = "shop_long")
    private Double shopLong;

    @Column(name = "active_date", nullable = false)
    private Date activeDate;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "ward_id")
    private Long wardId;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "terms_of_service")
    @Type(type = "text")
    private String termsOfService;

    @Column(name = "approved", columnDefinition = "bit")
    private Byte approved;

    @Column(name = "shop_config")
    @Type(type = "text")
    private String shopConfig;

    @Column(name = "zalo_chat")
    @Type(type = "text")
    private String zaloChat;

    @Column(name = "fb_chat")
    @Type(type = "text")
    private String fbChat;

    @Column(name = "district_logistic_code")
    private Integer districtLogisticCode;

    @Column(name = "city_logistic_code")
    private Integer cityLogisticCode;

    @Column(name = "ward_logistic_code")
    private Integer wardLogisticCode;

    /**
     * Ngay tao moi ban ghi
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * Ngay update cuoi cung ban ghi
     */
    @Column(name = "modified_date")
    private Date modifiedDate;

    /**
     * ID cua nguoi tao ban ghi
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Trạng thái hoạt động của shop
     */
    @Column(name = "active")
    private Boolean active;

    /**
     * ID của QTV active/deactive
     */
    @Column(name = "active_by_customer_id")
    private Long activeByCustomerId;

}
