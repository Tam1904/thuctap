package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.Utils.Response;
import com.sfin.sspareport.dto.OrderDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.SpaOrderSchedule;
import com.sfin.sspareport.repository.SpaOrderScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Service
public class OrderService {

    private Integer size =5;
    @Autowired
    private SpaOrderScheduleRepository orderRepository;

    public Response getOrderDetailShop(String date1, String date2, Integer page) throws ParseException{
        String [] s = Convert.convert(date1,date2);
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Tuple> orders = orderRepository.totalMoneyGroupByShopId(s[0],s[1],pageable);
        String [] s2 = orderRepository.getCountAndTotalMoney(s[0],s[1]).split(",");
        Response response = new Response();
        try{
            response.putDataValue("amount order", Long.parseLong(s2[0]));
        }
        catch (Exception e){
            response.putDataValue("amount order", Long.parseLong("0"));
        }
        try{
            response.putDataValue("total money", new BigDecimal(s2[1]).toString());
        }
        catch (Exception e){
            response.putDataValue("total money", "0");
        }
        List<ShopDTO> shopDTOS = Convert.convertShopV3(orders.getContent());
        response.putDataValue("list shop", shopDTOS);
        response.putDataValue("begin",1);
        response.putDataValue("end",orders.getTotalPages());
        response.putDataValue("current", page);
        return response;
    }


    public Response getOrder(String date1, String date2, Long shopId, Integer page) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Tuple> orders = orderRepository.getOrderByShopIdAndOderId(s[0],s[1],shopId,pageable);
        Response response = new Response();
        response.putDataValue("amount ", orders.getTotalElements());
        List<OrderDTO> orderDTOS = Convert.convertOrderV1(orders.getContent());
        response.putDataValue("list order", orderDTOS);
        response.putDataValue("begin",1);
        response.putDataValue("end",orders.getTotalPages());
        response.putDataValue("current", page);
        return response;
    }
}
