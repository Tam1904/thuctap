package com.sfin.sspareport.dto;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.repository.ShopProductsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.web.ProjectedPayload;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@NamedNativeQuery(
//        name = "convert_ShopDTO",
//        query =
//                "select s.shop_id as shopId, ss.commission_percentage as commissionPercentage, ss.remuneration as remuneration , s.shop_name as shopName, s.shop_phone as shopPhone  from SSHOP_MANAGEMENT.SHOP_PROFILE s left join POD_01.SPA_SALARY_DETAIL_SHOP ss on ss.shop_id = s.shop_id where s.chain_id = :chainId and s.created_date >= :date1 and s.created_date < :date2",
//        resultSetMapping = "convert_to_shopDTO_v1"
//)
//@SqlResultSetMapping(name = "convert_to_shopDTO_v1",classes = @ConstructorResult(
//        targetClass = ShopDTO.class,
//        columns = {
//                @ColumnResult(name = "shopId", type = Long.class),
//                @ColumnResult(name = "commissionPercentage", type = Float.class),
//                @ColumnResult(name = "remuneration", type = Long.class),
//                @ColumnResult(name = "shopName", type = String.class),
//                @ColumnResult(name = "shopPhone", type = String.class)
//        }
//))
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

    public ShopDTO(Long shopId, String shopName, BigDecimal commissionPercentage, BigInteger remuneration, String shopPhone
    , String wardName, String districtName, String proviceName){
        this.shopId = shopId;
        this.shopName = shopName;
        try {
            this.commissionPercentage = commissionPercentage.floatValue();
        }
        catch (Exception e){
            this.commissionPercentage = (float) 0.0;
        }
        try{
            this.remuneration = remuneration.longValue();
        }
        catch (Exception e){
            this.remuneration = (long) 0;
        }
        this.phone = shopPhone;
        this.address = wardName + " " + districtName + " " + proviceName;
    }

    public static ShopDTO convertV1(Tuple tuple){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(tuple.get(0,BigInteger.class).longValue());
        shopDTO.setShopName(tuple.get(3,String.class));
        try {
            shopDTO.setCommissionPercentage(tuple.get(1,BigDecimal.class).floatValue());
        }
        catch (Exception e){
            shopDTO.setCommissionPercentage((float) 0);
        }
        try{
            shopDTO.setRemuneration(tuple.get(2,BigInteger.class).longValue());
        }
        catch (Exception e){
            shopDTO.setRemuneration((long) 0);
        }
        shopDTO.setPhone(tuple.get(4,String.class));
        shopDTO.setAddress(tuple.get(5,String.class) + ", " + tuple.get(6,String.class)+ ", " +tuple.get(7,String.class));
        return shopDTO;
    }

    public static ShopDTO convertV2(Tuple tuple){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(tuple.get(0,BigInteger.class).longValue());
        shopDTO.setShopName(tuple.get(1,String.class));
        shopDTO.setNumberProduct(tuple.get(2,BigInteger.class).longValue());
        shopDTO.setPhone(tuple.get(3,String.class));
        shopDTO.setAddress(tuple.get(4,String.class));
        return shopDTO;
    }

    public static ShopDTO convertV3(Tuple tuple){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(tuple.get(0,BigInteger.class).longValue());
        shopDTO.setShopName(tuple.get(1,String.class));
        shopDTO.setPhone(tuple.get(2,String.class));
        shopDTO.setAddress(tuple.get(3,String.class));
        shopDTO.setOrderMoney(Convert.convertMoney(tuple.get(4,BigDecimal.class).toString()));
        return shopDTO;
    }



}
