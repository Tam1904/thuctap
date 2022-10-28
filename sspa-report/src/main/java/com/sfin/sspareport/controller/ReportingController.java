package com.sfin.sspareport.controller;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.Utils.ResponseData;
import com.sfin.sspareport.dto.ChainDTO;
import com.sfin.sspareport.dto.ProductDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.service.OrderService;
import com.sfin.sspareport.service.ProductService;
import com.sfin.sspareport.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping(path = "/spa/reporting", produces = "application/json")
@Api(value = "API to statistical chain shop, product and order", produces = "application/json")
public class ReportingController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    private int size = 5;

    @ApiOperation(value = "Get total product and list product between two days", produces = "application/json")
    @GetMapping("/productCreated")
    public ResponseData getProductsBetween(@RequestParam String beginDate, @RequestParam String endDate) throws ParseException {
        Long total = productService.getTotalProductByDate(beginDate, endDate);
        List<String> productString = productService.getByShopIdAndDate(beginDate, endDate);
        List<ShopDTO> productDTOS = Convert.convertListShop(productString);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("total", total);
        responseData.putDataValue("list Shop", productDTOS);
        return responseData;
    }

    @ApiOperation(value = "Get list product by shopId between two days", produces = "application/json")
    @GetMapping("/productCreated/shop/{shopId}")
    public ResponseData getProductsByShopIdAndDate(@PathVariable Long shopId, @RequestParam String beginDate, @RequestParam String endDate
            , @PathVariable("currentPage") Integer page) throws ParseException {
        int size = 5;
        Sort sort = Sort.by("product_id").ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ShopProducts> products = productService.getProductsByShopIAndDate(beginDate, endDate, shopId, pageable);
        List<ProductDTO> productDTOS = Convert.covertListProduct(products.getContent());
        int end = products.getTotalPages();
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("listProduct", productDTOS);
        responseData.putDataValue("begin", 1);
        responseData.putDataValue("end", end);
        responseData.putDataValue("page", page);
        return responseData;
    }

    @ApiOperation(value = "Get detail product", produces = "application/json")
    @GetMapping(path = "/products/{id}")
    public ResponseData getProductsById(@PathVariable("id") Long id) {
        try {
            ShopProducts product = productService.getProductsById(id);
            ProductDTO productDTO = ProductDTO.convert(product);
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("product", productDTO);
            return responseData;
        } catch (Exception e) {
            return ResponseData.badRequest();
        }
    }

    @ApiOperation(value = "get total order and total money", produces = "application/json")
    @GetMapping("/order")
    public ResponseData getCountOrder(@RequestParam String beginDate, @RequestParam String endDate) throws ParseException {
        ResponseData responseData = ResponseData.ok();
        String [] s = orderService.getCountAndTotalMoney(beginDate,endDate);
//        Long count = orderService.getTotalOrder(beginDate, beginDate);
        responseData.putDataValue("count", s[0]);
//        String totalMoney = orderService.getTotalMoney(beginDate, endDate);
        responseData.putDataValue("totalMoney", s[1]);
        List<String> order = orderService.getOrderDetailShop(beginDate, endDate);
        List<ShopDTO> shopDTOS = Convert.convertOrderShop(order);
        responseData.putDataValue("list order shop", Convert.convertOrderShop(order));
        return responseData;

    }

    @ApiOperation(value = "get list order by shopId", produces = "application/json")
    @GetMapping("/order/shop/{shopId}/")
    public ResponseData getDetailOrder(@PathVariable Long shopId, @RequestParam String beginDate, @RequestParam String endDate) throws ParseException {
        ResponseData responseData = ResponseData.ok();
        List<String> order = orderService.getOrder(beginDate, endDate, shopId);
        responseData.putDataValue("amount", order.size());
        responseData.putDataValue("shopId", Convert.convertOrder(order));
        return responseData;
    }

    @GetMapping("/chainCreated")
    public ResponseData getChain(@RequestParam String beginDate, @RequestParam String endDate) throws ParseException {
        ResponseData responseData = ResponseData.ok();
        List<ChainDTO> chainDTOS = shopService.getChain(beginDate, endDate);
        responseData.putDataValue("total chain", chainDTOS.size());
        responseData.putDataValue("list chain", chainDTOS);
        return responseData;
    }

    @GetMapping("/chain/{chainId}/shopCreated")
    public ResponseData getShopProfileByChain(@PathVariable("chainId") Long chainId, @RequestParam String beginDate
            , @RequestParam String endDate, @RequestParam Integer page) throws ParseException {
        Sort sort = Sort.by("shop_id").ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        ResponseData responseData = ResponseData.ok();
        List<ShopDTO> shopDTOS = shopService.getShopProfile(beginDate, endDate, chainId);
        responseData.putDataValue("total shop", shopDTOS.size());
        responseData.putDataValue("list shop", shopDTOS);
        return responseData;
    }

}
