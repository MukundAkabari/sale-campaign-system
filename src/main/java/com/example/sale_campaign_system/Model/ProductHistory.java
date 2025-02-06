package com.example.sale_campaign_system.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "product_history")
public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private int price;

    @Column(name = "dis_count_price")
    private double disCountPrice;

    @Column(name = "date")
    private LocalDate localDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDisCountPrice() {
        return disCountPrice;
    }

    public void setDisCountPrice(double disCountPrice) {
        this.disCountPrice = disCountPrice;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
