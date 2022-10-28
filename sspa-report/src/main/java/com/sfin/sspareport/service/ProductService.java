package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.dto.ProductDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.repository.ShopProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ShopProductsRepository productsRepository;

    public Long getTotalProductByDate(String date1, String date2) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        return productsRepository.findAllByCreatedDate(s[0],s[1]);
    }

    public List<String> getByShopIdAndDate(String date1, String date2) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        return productsRepository.findByShopAndDate(s[0],s[1]);
    }

    public List<ShopProducts> getProductsByShopIAndDate(String date1, String date2, Long shopId) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        return productsRepository.findShopProductsByShopIdAndDate(s[0],s[1],shopId);
    }

    public Page<ShopProducts> getProductsByShopIAndDate(String date1, String date2, Long shopId, Pageable pageable) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        return productsRepository.findProductsByShopAndDate(s[0],s[1],shopId,pageable);
    }
    public ShopProducts getProductsById(Long id){
        return productsRepository.findById(id).get();
    }



}
