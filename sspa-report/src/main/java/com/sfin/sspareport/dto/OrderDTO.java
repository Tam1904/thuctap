package com.sfin.sspareport.dto;

import com.sfin.sspareport.Utils.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    private Long customerId;

    private String customerName;

    private String money;

    private Timestamp createdDate;

    public static OrderDTO convertV1(Tuple tuple) throws ParseException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(tuple.get(0, BigInteger.class).longValue());
        orderDTO.setCustomerId(tuple.get(1,BigInteger.class).longValue());
        orderDTO.setCustomerName(tuple.get(2,String.class));
        orderDTO.setMoney(tuple.get(4, BigDecimal.class).toString());
        orderDTO.setCreatedDate(tuple.get(3, Timestamp.class));
        return orderDTO;
    }

}
