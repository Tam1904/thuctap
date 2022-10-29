package com.sfin.sspareport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainDTO {

    private Long chainId;

    private String name;

    private String subdomain;

    private String createdDate;

    public static ChainDTO Convert(Tuple tuple) throws ParseException {
        ChainDTO chainDTO = new ChainDTO();
        chainDTO.setChainId(tuple.get(0, BigInteger.class).longValue());
        chainDTO.setName(tuple.get(1,String.class));
        chainDTO.setSubdomain(tuple.get(2,String.class));
        chainDTO.setCreatedDate(tuple.get(3, Timestamp.class).toString());
        return chainDTO;
    }
}
