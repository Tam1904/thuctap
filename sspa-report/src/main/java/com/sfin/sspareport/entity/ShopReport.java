package com.sfin.sspareport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "SHOP_REPORT", schema = "SSHOP_REPORTING", catalog = "SSHOP_REPORTING")
@NoArgsConstructor
@AllArgsConstructor
public class ShopReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_date", nullable = false)
    private Date reportDate;

    @Column(name = "amount_chain")
    private Long amountChain;

    @Column(name = "amount_shop")
    private Long amountShop;

    @Column(name = "amount_product")
    private Long amountProduct;

    @Column(name = "amount_order")
    private Long amountOrder;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    public ShopReport(Date reportDate, Long amountChain, Long amountShop, Long amountProduct, Long amountOrder, BigDecimal totalMoney){
        this.reportDate = reportDate;
        this.amountChain =amountChain;
        this.amountShop = amountShop;
        this.amountProduct = amountProduct;
        this.amountOrder = amountOrder;
        this.totalMoney = totalMoney;
    }

}
