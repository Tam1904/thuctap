package com.sfin.sspareport.dto;

import com.sfin.sspareport.entity.ShopProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private String image;

    public static ProductDTO convert(ShopProducts product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getPrice());
        productDTO.setImage(product.getImageUri());
        return productDTO;
    }

//    public static ProductDTO convertV1(Tuple tuple){
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setProductId(tuple.get(0, BigInteger));
//    }
}
