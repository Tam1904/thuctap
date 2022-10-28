package com.sfin.sspareport.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "SHOP_PRODUCTS", schema = "POD_01", catalog = "POD_01")
public class ShopProducts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @Column(name = "system_product_id")
    private Long systemProductId;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "unit")
    private String unit;

    @Column(name = "sold_amount")
    private Integer soldAmount;

    @Column(name = "main_image_url")
    private String mainImageUrl;

    @Column(name = "images_url")
    @Type(type = "text")
    private String imagesUrl;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "price_before_discount")
    private BigDecimal priceBeforeDiscount;

    @Column(name = "size")
    private String size;

    @Column(name = "weight")
    private String weight;

    @Column(name = "color")
    private String color;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "expiry_note")
    private String expiryNote;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "attributes")
    @Type(type = "text")
    private String attributes;

    @Column(name = "options")
    @Type(type = "text")
    private String options;

    @Column(name = "active_date")
    private Date activeDate;

    @Column(name = "active_user_id")
    private Long activeUserId;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "modified_user_id")
    private Long modifiedUserId;

    @Column(name = "main_image_id")
    private Long mainImageId;

    /**
     * type=0: sản phẩm bán
     * type=1: sản phẩm chế biến (ko trừ quản lý kho-đã update code DONE)
     * type=2: sản phẩm nguyên liệu
     * type = 3 : sản phẩm dropship ( không trừ trong kho shop)
     */
    @Column(name = "type")
    private Integer type;

    @Column(name = "bar_code")
    @Type(type = "text")
    private String barCode;

    @Column(name = "is_sell")
    private Integer sell;

    /**
     * Tham chiếu SYSTEM_SUPLLIER_PRODUCT
     */
    @Column(name = "supplier_direct_product_id")
    private Long supplierDirectProductId;

    /**
     * Tham chiếu SYSTEM_SUPLLIER
     */
    @Column(name = "supplier_direct_id")
    private Long supplierDirectId;

    @Column(name = "cweight")
    private Double cweight;

    @Column(name = "cwidth")
    @Type(type = "text")
    private String cwidth;

    @Column(name = "cheight")
    @Type(type = "text")
    private String cheight;

    @Column(name = "clength")
    @Type(type = "text")
    private String clength;

    @Column(name = "cmetadata")
    @Type(type = "text")
    private String cmetadata;

    @Column(name = "rate_count")
    private Integer rateCount;

    @Column(name = "modification_time", nullable = false)
    private Date modificationTime;

    @Column(name = "image_uri")
    @Type(type = "text")
    private String imageUri;

}
