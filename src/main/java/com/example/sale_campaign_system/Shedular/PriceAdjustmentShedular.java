package com.example.sale_campaign_system.Shedular;

import com.example.sale_campaign_system.Model.Campaign;
import com.example.sale_campaign_system.Model.CampaignDiscount;
import com.example.sale_campaign_system.Model.Product;
import com.example.sale_campaign_system.Model.ProductHistory;
import com.example.sale_campaign_system.Repository.CampaignRepository;
import com.example.sale_campaign_system.Repository.ProductHistoryRepository;
import com.example.sale_campaign_system.Repository.ProductRepository;
import com.example.sale_campaign_system.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PriceAdjustmentShedular {


    @Autowired
    ProductHistoryRepository productHistoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ProductService productService;

    @Scheduled(cron = "0 0 0 * * *")
    public void AdjuctShedular(){

        LocalDate today=LocalDate.now();
        List<Campaign> activesale = campaignRepository.selectCampaignAllToday(today);
        System.out.println();
        for (Campaign c:activesale){
            List<CampaignDiscount> discounts= c.getCampaignDiscountList();
            System.out.println();
            for (CampaignDiscount discount : discounts){
                Product product=productRepository.findById(discount.getProductId()).orElse(null);
                System.out.println();
                if(product != null){
                    double discountAmount=((product.getCurrentPrice() * discount.getDiscouuntPercentage()/100));
                    double newPrice = (product.getCurrentPrice()-discountAmount);
                    System.out.println("price will upadte");
                    if(newPrice >=0){
                        product.setCurrentPrice(newPrice);
                        product.setDiscount(discount.getDiscouuntPercentage());
                        productRepository.save(product);
                        productService.saveHistory(product);
                    }
                }
            }
        }
    }
    @Scheduled(cron = "0 0 0 * * *")
    public void revartShedular(){

       LocalDate today=LocalDate.now();
        List<Campaign> endsale = campaignRepository.endcampaignalltoday(today);

        for (Campaign c:endsale){
            List<CampaignDiscount> discounts= c.getCampaignDiscountList();

            for (CampaignDiscount discount : discounts){
                Product product=productRepository.findById(discount.getProductId()).orElse(null);

                if(product != null){
                    System.out.println();
                    LocalDate today1 = LocalDate.parse(c.getStartDate());

                    ProductHistory productHistory=productHistoryRepository.findTopByProductIdAndDate(product.getpId(),today1);
                    System.out.println();
                    if(productHistory != null){
                        double previousPrice = productHistory.getDisCountPrice();
                        product.setCurrentPrice(productHistory.getPrice()+previousPrice);

                        productRepository.save(product);
                        productService.saveHistory(product);
                    }
                }
            }
        }
    }

}
