package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.dto.ChainDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.repository.ShopProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopProfileRepository shopRepository;

    public List<ChainDTO> getChain(String date1, String date2) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        List<ChainDTO> chainDTOS = Convert.convertChain(shopRepository.getChain(s[0],s[1]));
        return chainDTOS;
    }

    public List<String> getShopId(String date1, String date2,Long chainId) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        return shopRepository.getShopByChainId(chainId, s[0],s[1]);
    }

    public List<ShopDTO> getShopProfile(String date1, String date2, Long chainId) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        List<String> s1 = shopRepository.getShopByChainId(chainId,s[0],s[1]);
        List<ShopDTO> shopDTOS = Convert.convertShopV2(s1);
        return shopDTOS;
    }
}
