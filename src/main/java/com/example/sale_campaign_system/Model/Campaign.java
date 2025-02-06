package com.example.sale_campaign_system.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int cId;
    @Column(name = "title")
    private String title;
    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    private String startDate;
    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    private String endDate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "campaign")
    @JsonBackReference
    private List<CampaignDiscount> campaignDiscountList;

    public Campaign(){
        this.cId=genereteUniq();
    }
    public int genereteUniq(){
        String uuid= UUID.randomUUID().toString().replace("-"," ");
        String account=uuid.substring(0,6);
        return (int)(Integer.parseInt(account,16)%1000000L);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignDiscountList=" + (campaignDiscountList != null?campaignDiscountList.size():0) +
                '}';
    }

    public List<CampaignDiscount> getCampaignDiscountList() {
        return campaignDiscountList;
    }

    public void setCampaignDiscountList(List<CampaignDiscount> campaignDiscountList) {
        this.campaignDiscountList = campaignDiscountList;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
