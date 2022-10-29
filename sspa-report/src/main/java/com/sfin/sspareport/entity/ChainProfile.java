package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Hồ sơ của chuỗi hàng
 */
@Data
@Entity
@Table(name = "CHAIN_PROFILE", schema = "SSHOP_MANAGEMENT", catalog = "SSHOP_MANAGEMENT")
public class ChainProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chain_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chainId;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_domain")
    private String subDomain;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "image")
    @Type(type = "text")
    private String image;

    @Column(name = "status")
    private Integer status;

}
