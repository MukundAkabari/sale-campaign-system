package com.example.sale_campaign_system.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "campaign_discount")
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "discount_percentage")
    private int discouuntPercentage;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @JsonBackReference
    private Campaign campaign;

    @Column(name = "product_id")
    private int productId;

    @Override
    public String toString() {
        return "CampaignDiscount{" +
                "id=" + id +
                ", discouuntPercentage=" + discouuntPercentage +
                ", campaign=" + campaign +
                ", productId=" + productId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscouuntPercentage() {
        return discouuntPercentage;
    }

    public void setDiscouuntPercentage(int discouuntPercentage) {
        this.discouuntPercentage = discouuntPercentage;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
