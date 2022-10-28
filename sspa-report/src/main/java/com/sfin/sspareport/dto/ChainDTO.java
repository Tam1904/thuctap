package com.sfin.sspareport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static ChainDTO Convert(String s) throws ParseException {
        ChainDTO chainDTO = new ChainDTO();
        String [] w  = s.split(",");
        chainDTO.setChainId(Long.parseLong(w[0]));
        chainDTO.setName(w[1]);
        chainDTO.setSubdomain(w[2]);
        chainDTO.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(w[3])));
        return chainDTO;
    }
}
