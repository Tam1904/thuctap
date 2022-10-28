package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CHAIN_EMPLOYMENT_PROFILE", schema = "POD_01", catalog = "POD_01")
public class ChainEmploymentProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sys_user_id")
    private Long sysUserId;

    /**
     * id của chủ sở hữu chuỗi
     */
    @Column(name = "chain_id")
    private Long chainId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "image")
    @Type(type = "text")
    private String image;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0 - free, 1 - đang làm
     */
    @Column(name = "check_free")
    private Integer checkFree;

    @Column(name = "checkin_time")
    private Date checkinTime;

    @Column(name = "image_uri")
    @Type(type = "text")
    private String imageUri;

    /**
     * check xem bản ghi bị xóa hay chưa
     */
    @Column(name = "is_deleted")
    private Boolean deleted;

}
