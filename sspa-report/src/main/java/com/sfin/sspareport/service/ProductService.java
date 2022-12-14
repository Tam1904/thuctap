package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.Utils.Response;
import com.sfin.sspareport.dto.ProductDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.repository.ShopProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private Integer size =5;
    @Autowired
    private ShopProductsRepository productsRepository;


    public Response getByShopIdAndDate(String date1, String date2, Integer page) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Tuple> pages = productsRepository.findByShopAndDate(s[0],s[1],pageable);
        Long total = productsRepository.findAllByCreatedDate(s[0],s[1]);
        Response response = new Response();
        List<ShopDTO> shopDTOS = Convert.convertShopV2(pages.getContent());
        response.putDataValue("begin",1);
        response.putDataValue("end", pages.getTotalPages());
        response.putDataValue("current",page);
        response.putDataValue("amount product", total);
        response.putDataValue("list shop", shopDTOS);
        response.putDataValue("amount product", pages.getTotalElements());
        return response;
    }

    public Response getProductsByShopIdAndDate(String date1, String date2, Long shopId, Integer page) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        Pageable pageable = PageRequest.of(page-1,size);
        Page<ShopProducts> pages = productsRepository.findProductsByShopAndDate(s[0],s[1],shopId,pageable);
        Response response = new Response();
        response.putDataValue("amount product", pages.getTotalElements());
        response.putDataValue("begin",1);
        response.putDataValue("end",pages.getTotalPages());
        response.putDataValue("current page", page);
        response.putDataValue("list product",Convert.covertListProduct(pages.getContent()));
        return response;
    }
    public ShopProducts getProductsById(Long id){
        return productsRepository.findById(id).get();
    }

}
