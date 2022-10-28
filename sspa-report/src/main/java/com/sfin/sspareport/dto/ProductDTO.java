package com.sfin.sspareport.dto;

import com.sfin.sspareport.entity.ShopProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
}
