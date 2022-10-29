package com.sfin.sspareport.Utils;

import com.sfin.sspareport.dto.ChainDTO;
import com.sfin.sspareport.dto.OrderDTO;
import com.sfin.sspareport.dto.ProductDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;

import javax.persistence.Tuple;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Convert {
    public static Date convertStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }

    public static String convertDateToString(Date date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date rollDate(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date date1 = convertStringToDate(date);
        calendar.setTime(date1);
        calendar.roll(Calendar.DATE,1);
        return calendar.getTime();
    }

    public static Date convertStringToMonth(String date) throws ParseException {
        return new SimpleDateFormat("MM-yyyy").parse(date);
    }

    public String convertMonthToString(Date date) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date rollMonth(String date) throws ParseException {
        Date date1 = convertStringToMonth(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.roll(Calendar.MONTH,1);
        return calendar.getTime();
    }

    public static List<ProductDTO> covertListProduct(List<ShopProducts> products){
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(ShopProducts products1 : products){
            ProductDTO productDTO = ProductDTO.convert(products1);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public static List<ShopDTO> convertListShop(List<String> list){
        List<ShopDTO> shop = new ArrayList<>();
        for(String s : list){
            shop.add(ShopDTO.convert(s));
        }
        return shop;
    }

    public static String [] convert(String date1, String date2) throws ParseException {
        String [] s = new String [2];
        s[0] = Convert.convertDateToString(Convert.convertStringToDate(date1));
        s[1] = Convert.convertDateToString(Convert.rollDate(date2));
        return  s;
    }

     public static List<OrderDTO> convertOrder(List<String> orders) throws ParseException {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(String s: orders){
            orderDTOS.add(OrderDTO.convert(s));
        }
        return orderDTOS;
     }

    public static List<OrderDTO> convertOrderV1(List<Tuple> tuples) throws ParseException {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Tuple tuple : tuples){
            orderDTOS.add(OrderDTO.convertV1(tuple));
        }
        return orderDTOS;
    }

     public static List<ShopDTO> convertOrderShop(List<String> order) throws ParseException{
        List<ShopDTO> shopDTOS = new ArrayList<>();
        for(String s: order){
            shopDTOS.add(ShopDTO.convertV1(s));
        }
        return shopDTOS;
     }

     public static HashMap<Long, String> convertMapOrder(List<ShopDTO> dtos){
        HashMap<Long,String> map = new HashMap<>();
        for(ShopDTO o : dtos){
            map.put(o.getShopId(),o.getOrderMoney());
        }
        return map;
     }

     public static List<ShopDTO> convertShopV2(List<Tuple> tuples){
        List<ShopDTO> shopDTOS = new ArrayList<>();
        for(Tuple w : tuples){
            shopDTOS.add(ShopDTO.convertV2(w));
        }
        return shopDTOS;
     }

    public static List<ShopDTO> convertShopV3(List<Tuple> tuples){
        List<ShopDTO> shopDTOS = new ArrayList<>();
        for(Tuple w : tuples){
            shopDTOS.add(ShopDTO.convertV3(w));
        }
        return shopDTOS;
    }

     public static List<ChainDTO> convertChain(List<Tuple> tuples) throws ParseException {
        List<ChainDTO> chainDTOS = new ArrayList<>();
        for(Tuple w : tuples){
            chainDTOS.add(ChainDTO.Convert(w));
        }
        return chainDTOS;
     }

//     public static List<ProductDTO> covertV2(List<Tuple> tuples){
//
//     }

     public static String convertMoney(String s){
        Object number;
        try{
            number = Long.parseLong(s);
        }
        catch (Exception e){
            number = Float.parseFloat(s);
        }
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi","VN"));
        return numberFormat.format(number);
     }
}

