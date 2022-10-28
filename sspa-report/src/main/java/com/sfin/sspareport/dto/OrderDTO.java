package com.sfin.sspareport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    private String customerName;

    private String money;

    private String createdDate;

    public static OrderDTO convert(String s) throws ParseException {
        OrderDTO orderDTO = new OrderDTO();
        String []w = s.split(",");
        orderDTO.setOrderId(Long.parseLong(w[0]));
        orderDTO.setCustomerName(w[1]);
        orderDTO.setMoney(NumberFormat.getInstance(new Locale("vi","VN")).format(Long.parseLong(w[3])));
        orderDTO.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(w[2])));
        return orderDTO;
    }

}
