package com.example.sale_campaign_system.Service;

import com.example.sale_campaign_system.Model.Campaign;
import com.example.sale_campaign_system.Model.CampaignDiscount;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CampaignService {
    @Autowired
    CampaignRepository campaignRepository;





    public ResponseDTO<Campaign> saveCampaign(Campaign campaign) {

        try {
            if (campaign.getCampaignDiscountList() != null) {
                for (CampaignDiscount discount : campaign.getCampaignDiscountList()) {
                    discount.setCampaign(campaign);
                }
            }
            Campaign savedCampaign = campaignRepository.save(campaign);
            return new ResponseDTO<>(savedCampaign,"save campaign sucssesfully", HttpStatus.ACCEPTED);
        }catch (Exception e){
            System.out.println();
            return new ResponseDTO<>(null,"trobal to save campaign"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
