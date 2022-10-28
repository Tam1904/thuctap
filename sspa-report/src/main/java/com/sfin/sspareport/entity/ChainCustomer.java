package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CHAIN_CUSTOMER", schema = "POD_01", catalog = "POD_01")
public class ChainCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Local customer id
     */
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    /**
     * id chuỗi
     */
    @Column(name = "chain_id", nullable = false)
    private Long chainId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    @Column(name = "customer_gender")
    private Integer customerGender;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_dob")
    private String customerDob;

    @Column(name = "customer_note")
    private String customerNote;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_facebook")
    private String customerFacebook;

    @Column(name = "customer_zalo")
    private String customerZalo;

    @Column(name = "customer_branch")
    private String customerBranch;

    @Column(name = "customer_source")
    private Integer customerSource;

    @Column(name = "customer_old_new")
    private Integer customerOldNew;

    @Column(name = "customer_rank")
    private Integer customerRank;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "customer_ward_id")
    private Integer customerWardId;

    @Column(name = "customer_ward_name")
    @Type(type = "text")
    private String customerWardName;

    @Column(name = "customer_district_id")
    private Integer customerDistrictId;

    @Column(name = "customer_district_name")
    @Type(type = "text")
    private String customerDistrictName;

    @Column(name = "customer_province_id")
    private Integer customerProvinceId;

    @Column(name = "customer_province_name")
    @Type(type = "text")
    private String customerProvinceName;

    @Column(name = "image_uri")
    @Type(type = "text")
    private String imageUri;

}
