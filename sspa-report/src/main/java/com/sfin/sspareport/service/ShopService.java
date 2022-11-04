package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.Utils.Response;
import com.sfin.sspareport.dto.ChainDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.repository.ShopProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopProfileRepository shopRepository;

    private Integer size = 5;

    public Response getChain(String date1, String date2, Integer page) throws ParseException {
        Pageable pageable = PageRequest.of(page-1,5);
        String[] s = Convert.convert(date1, date2);
        Page<Tuple> pages = shopRepository.getChain(s[0], s[1],pageable);
        Long totalShop = shopRepository.getTotalShop(s[0],s[1]);
        List<ChainDTO> chainDTOS = Convert.convertChain(pages.getContent());
        Response response = new Response();
        response.putDataValue("amount chain", pages.getTotalElements());
        response.putDataValue("amount shop",totalShop);
        response.putDataValue("begin", 1);
        response.putDataValue("current page", page);
        response.putDataValue("end", pages.getTotalPages());
        response.putDataValue("list shop", chainDTOS);
        return response;
    }

    public Response getShopProfileV2(String date1, String date2, Long chainId, Integer page) throws ParseException {
        Pageable pageable = PageRequest.of(page - 1, size);
        String[] s = Convert.convert(date1, date2);
        Page<Tuple> s1 = shopRepository.getShopByChainId(chainId, s[0], s[1],pageable);
        List<ShopDTO> shopDTOS = new ArrayList<>();
        for(Tuple tuple : s1.getContent()){
            shopDTOS.add(ShopDTO.convertV1(tuple));
        }
        Response response = new Response();
        response.putDataValue("amount shop", s1.getTotalElements());
        response.putDataValue("begin", 1);
        response.putDataValue("current page", page);
        response.putDataValue("end", s1.getTotalPages());
        response.putDataValue("list shop", shopDTOS);
        return response;
    }
}
