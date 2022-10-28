package com.sfin.sspareport.dto;

import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.repository.ShopProductsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    private Long shopId;

    private String shopName;

    private Long numberProduct;

//    private List<String> shopIds;

    private String orderMoney;

    private Float commissionPercentage;

    private Long remuneration;

    private String phone;

    private String address;

    public static ShopDTO convert(String s){
        String [] w = s.split(",");
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(Long.parseLong(w[0]));
        shopDTO.setShopName(w[1]);
        shopDTO.setNumberProduct(Long.parseLong(w[2]));
        return shopDTO;
    }

    public static ShopDTO convertV1(String s){
        String [] w = s.split(",");
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(Long.parseLong(w[0]));
        shopDTO.setShopName(w[1]);
        shopDTO.setOrderMoney(NumberFormat.getInstance(new Locale("vi","VN")).format(Long.parseLong(w[2])));
        return shopDTO;
    }

    public static ShopDTO convertV2(String s){
        String [] w = s.split(",");
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(Long.parseLong(w[0]));
        shopDTO.setOrderMoney(NumberFormat.getInstance(new Locale("vi","VN")).format(Long.parseLong(w[1])));
        return shopDTO;
    }

    public static ShopDTO convertV3(String s){
        String []w = s.split(",");
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(Long.parseLong(w[0]));
        try {
            shopDTO.setCommissionPercentage(Float.parseFloat(w[1]));
        }
        catch (Exception e){
            shopDTO.setCommissionPercentage((float) 0);
        }
        try {
            shopDTO.setRemuneration(Long.parseLong(w[2]));
        }
        catch (Exception e){
            shopDTO.setRemuneration((long)0);
        }
        shopDTO.setShopName(w[3]);
        shopDTO.setPhone(w[4]);
        shopDTO.setAddress(w[5] + " " + w[6] + " " + w[7]);
        return shopDTO;
    }

}
