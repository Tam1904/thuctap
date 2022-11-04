package com.sfin.sspareport.dto;

import com.sfin.sspareport.entity.ShopReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private String date;

    private Long amountChain;

    private Long amountShop;

    private Long amountProduct;

    private Long amountOrder;

    private BigDecimal totalMoney;

    public static ReportDTO convertV2(String s){
        ReportDTO reportDTO = new ReportDTO();
        String [] w = s.split(",");
        reportDTO.setDate(w[0]);
        reportDTO.setAmountChain(Long.parseLong(w[1]));
        reportDTO.setAmountShop(Long.parseLong(w[2]));
        reportDTO.setAmountProduct(Long.parseLong(w[3]));
        reportDTO.setAmountOrder(Long.parseLong(w[4]));
        reportDTO.setTotalMoney(new BigDecimal(w[5]));
        return reportDTO;
    }
}