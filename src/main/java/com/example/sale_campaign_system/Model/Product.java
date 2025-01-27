package com.example.sale_campaign_system.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_id")
    private int pId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    //mrp=marketion retail price
    @Column(name = "mrp")
    private double mrp;
    @Column(name = "current_price")
    private double currentPrice;
}
