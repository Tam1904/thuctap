package com.sfin.sspareport.service;

import com.sfin.sspareport.Utils.Convert;
import com.sfin.sspareport.Utils.Response;
import com.sfin.sspareport.dto.ReportDTO;
import com.sfin.sspareport.entity.ShopReport;
import com.sfin.sspareport.repository.ShopReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ShopReportRepository repository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    public void updateReport() throws ParseException {
        Date date = repository.getMinDate();
        Date date1 = new Date(System.currentTimeMillis());
        while(date.before(date1)){
            String date2 = Convert.convertDateToDate(date.toString());
            ShopReport shopReport = update(date2);
            date = rollDate(date);
//            repository.save(shopReport);
//            System.out.println(shopReport);
        }
    }

    public Response getPercent(Date date1){
        ReportDTO dto1  = ReportDTO.convertV2(repository.getTotalReport(date1));
        ReportDTO dto2 = ReportDTO.convertV2(repository.getTotalReport(subMonth(date1)));
        Response response = new Response();
        response.putDataValue("percent chain", Math.round (1.0f*dto1.getAmountChain()/dto2.getAmountChain()*100));
        response.putDataValue("percent shop", Math.round(1.0f*dto1.getAmountShop()/dto2.getAmountShop()*100));
        response.putDataValue("percent product", Math.round(1.0f*dto1.getAmountProduct()/dto2.getAmountProduct()*100));
        response.putDataValue("percent order", Math.round(1.0f*dto1.getAmountOrder()/dto2.getAmountOrder()*100));
        response.putDataValue("percent money", (dto1.getTotalMoney().divide(dto2.getTotalMoney(),0, RoundingMode.HALF_UP).toString()));
        return response;
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void updateEveryDay() throws ParseException{
        Date date = new Date(System.currentTimeMillis());
        String date2 = Convert.convertDateToDate(date.toString());
        ShopReport shopReport = update(date2);
        repository.save(shopReport);
    }

    public Date rollDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        return new Date(calendar.getTime().getTime());
    }

    public ShopReport update(String date) throws ParseException {
        Response response = shopService.getChain(date,date,1);
        Long amountChain = (Long) response.getData().get("amount chain");
        Long amountShop = (Long) response.getData().get("amount shop");
        response = productService.getByShopIdAndDate(date,date,1);
        Long amountProduct = (Long) response.getData().get("amount product");
        response = orderService.getOrderDetailShop(date,date,1);
        Long amountOrder = (Long) response.getData().get("amount order");
        BigDecimal totalMoney = new BigDecimal((String) response.getData().get("total money"));
        ShopReport shopReport = new ShopReport(new Date(new SimpleDateFormat("dd-MM-yyyy").parse(date).getTime()),amountChain,amountShop,amountProduct,amountOrder,totalMoney);
        return shopReport;
    }

    public Date subMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1);
        return new Date(calendar.getTime().getTime());
    }
}


