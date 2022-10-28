package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.entity.SpaOrderSchedule;
import com.sfin.sspareport.repository.SpaOrderScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Service
public class OrderService {

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

    public String [] getCountAndTotalMoney(String date1, String date2){
        String [] s = orderRepository.getCountAndTotalMoney(date1,date2).split(",");
        s[1] = Convert.convertMoney(s[1]);
        return s;
    }

    public List<String> getOrderDetailShop(String date1, String date2) throws ParseException{
        String [] s = Convert.convert(date1,date2);
        List<String> orders = orderRepository.totalMoneyGroupByShopId(s[0],s[1]);
        return orders;
    }

    List<SpaOrderSchedule> getOrderByShopId(String date1, String date2, Long shopId) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        List<SpaOrderSchedule> orders = orderRepository.getOrderByShopId(s[0],s[1],shopId);
        return orders;
    }

    public List<String> getOrder(String date1, String date2, Long shopId) throws ParseException {
        String [] s = Convert.convert(date1,date2);
        List<String> orders = orderRepository.getOrderByShopIdAndOderId(s[0],s[1],shopId);
        return orders;
    }
}
