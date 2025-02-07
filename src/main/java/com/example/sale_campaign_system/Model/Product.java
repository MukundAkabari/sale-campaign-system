package com.example.sale_campaign_system.Model;

import ch.qos.logback.core.model.INamedModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name = "p_id")
    private int pId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    //mrp=marketing retail price
    @Column(name = "mrp")
    private double mrp;
    @Column(name = "current_price")
    private double currentPrice;
    @Column(name = "discount")
    private double discount;
    @Column(name = "inventory_count")
    private int inventoryCount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductHistory> productHistoryList;

//    public Product(){
//        this.pId=generetuniqid();
//    }

//    public int generetuniqid(){
//        String uuid= UUID.randomUUID().toString().replace("-"," ");
//        String accountno=uuid.substring(0,6);
//        return (int)(Integer.parseInt(accountno,16)%1000000L);
//    }

    @Override
    public String toString() {
        return"Product{"+
            "pId" + pId +
                "title"+ title+
                "description"+description+
                "mrp"+mrp+
                "currentPrice"+currentPrice+
                "discount"+discount+
                "inventoryCount"+inventoryCount+
                '}';
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public List<ProductHistory> getProductHistoryList() {
        return productHistoryList;
    }

    public void setProductHistoryList(List<ProductHistory> productHistoryList) {
        this.productHistoryList = productHistoryList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
