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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Service
public class OrderService {

    private Integer size =5;
    @Autowired
    private SpaOrderScheduleRepository orderRepository;

    public Long getTotalOrder(String date1, String date2) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        return orderRepository.countByCreatedDateBetween(s[0],s[1]);
    }

    public String getTotalMoney(String date1, String date2) throws ParseException{
        String [] s = Convert.convert(date1,date2);
        Locale locale = new Locale("vi","VN");
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        Long total = orderRepository.totalMoney(s[0],s[1]);
        return numberFormat.format(total);
    }

    public String [] getCountAndTotalMoney(String date1, String date2) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        String [] s2 = orderRepository.getCountAndTotalMoney(s[0],s[1]).split(",");
        s2[1] = Convert.convertMoney(s[1]);
        return s2;
    }

    public Response getOrderDetailShop(String date1, String date2, Integer page) throws ParseException{
        String [] s = Convert.convert(date1,date2);
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Tuple> orders = orderRepository.totalMoneyGroupByShopId(s[0],s[1],pageable);
        String [] s2 = orderRepository.getCountAndTotalMoney(s[0],s[1]).split(",");
        s2[1] = Convert.convertMoney(s2[1]);
        Response response = new Response();
        response.putDataValue("amount order", s2[0]);
        response.putDataValue("total money", s2[1]);
        List<ShopDTO> shopDTOS = Convert.convertShopV3(orders.getContent());
        response.putDataValue("list shop", shopDTOS);
        response.putDataValue("begin",1);
        response.putDataValue("end",orders.getTotalPages());
        response.putDataValue("current", page);
        return response;
    }

    List<SpaOrderSchedule> getOrderByShopId(String date1, String date2, Long shopId) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        List<SpaOrderSchedule> orders = orderRepository.getOrderByShopId(s[0],s[1],shopId);
        return orders;
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
