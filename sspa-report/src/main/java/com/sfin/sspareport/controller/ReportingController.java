package com.sfin.sspareport.controller;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.Utils.Response;
import com.sfin.sspareport.Utils.ResponseData;
import com.sfin.sspareport.dto.ChainDTO;
import com.sfin.sspareport.dto.ProductDTO;
import com.sfin.sspareport.dto.ReportDTO;
import com.sfin.sspareport.dto.ShopDTO;
import com.sfin.sspareport.entity.ShopProducts;
import com.sfin.sspareport.entity.ShopProfile;
import com.sfin.sspareport.service.OrderService;
import com.sfin.sspareport.service.ProductService;
import com.sfin.sspareport.service.ReportService;
import com.sfin.sspareport.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private ReportService reportService;

    private int size = 5;

    @ApiOperation(value = "Get total product and list product between two days", produces = "application/json")
    @GetMapping("/productCreated")
    public Response getProductsBetween(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam Integer page) throws ParseException {
        Response response = productService.getByShopIdAndDate(beginDate, endDate,page);
        return response;
    }

    @ApiOperation(value = "Get list product by shopId between two days", produces = "application/json")
    @GetMapping("/productCreated/shop/{shopId}")
    public Response getProductsByShopIdAndDate(@PathVariable Long shopId, @RequestParam String beginDate, @RequestParam String endDate
            , @RequestParam("page") Integer page) throws ParseException {
        Response response = productService.getProductsByShopIdAndDate(beginDate,endDate,shopId,page);
        return response;
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
    public Response getCountOrder(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam Integer page) throws ParseException {
        Response response = orderService.getOrderDetailShop(beginDate,endDate,page);
        return response;

    }

    @ApiOperation(value = "get list order by shopId", produces = "application/json")
    @GetMapping("/order/shop/{shopId}")
    public Response getDetailOrder(@PathVariable Long shopId, @RequestParam String beginDate, @RequestParam String endDate, @RequestParam Integer page) throws ParseException {
        Response response = orderService.getOrder(beginDate, endDate, shopId,page);
        return response;
    }

    @GetMapping("/chainCreated")
    public Response getChain(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam Integer page) throws ParseException {
        Response response = shopService.getChain(beginDate, endDate,page);
        return response;
    }

    @GetMapping("/chain/{chainId}/shopCreated")
    public Response getShopProfileByChain(@PathVariable("chainId") Long chainId, @RequestParam String beginDate
            , @RequestParam String endDate, @RequestParam Integer page) throws ParseException {
        Response response = shopService.getShopProfileV2(beginDate, endDate, chainId,page);
        return response;
    }

    @PutMapping("/updated")
    public void updateReport() throws ParseException {
        reportService.updateReport();
    }

    @GetMapping("/report")
    public Response report(@RequestParam String month, @RequestParam String year) throws ParseException{
        Date date3 = new Date(new SimpleDateFormat("MM-yyyy").parse(month + "-" + year).getTime());
        System.out.println(date3);
        Response response = reportService.getPercent(date3);
        return response;
    }

}
