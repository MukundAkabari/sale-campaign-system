package com.example.sale_campaign_system.Controller;

import com.example.sale_campaign_system.Model.Campaign;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Service.CampaignService;
import com.example.sale_campaign_system.Shedular.PriceAdjustmentShedular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("campaign")
public class CampaingController {
    @Autowired
    CampaignService campaignService;

    @Autowired
    PriceAdjustmentShedular priceAdjustmentShedular;

    @PostMapping("addCampaign")
    public ResponseDTO<Campaign> addcompaign(@RequestBody Campaign campaign){
            return campaignService.saveCampaign(campaign);

    }
    @GetMapping("getCmapaign")
    public List<Campaign> getCampaign(){
          return  campaignService.getCampaign();
    }
//    @PutMapping("adjustPrice")
//    public void revartShedular(){
//        priceAdjustmentShedular.revartShedular();
//    }
}
