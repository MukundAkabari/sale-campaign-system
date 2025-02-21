package com.example.sale_campaign_system.Controller;

import com.example.sale_campaign_system.Model.Campaign;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("campaign")
public class CampaingController {
    @Autowired
    CampaignService campaignService;

    @PostMapping("addCampaign")
    public ResponseDTO<Campaign> addcompaign(@RequestBody Campaign campaign){
            return campaignService.saveCampaign(campaign);

    }
}
