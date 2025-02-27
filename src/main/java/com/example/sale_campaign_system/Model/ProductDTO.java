package com.example.sale_campaign_system.Model;

import java.awt.print.Pageable;

public class ProductDTO {

    private String name;
    private double price;
    private String description;

    public ProductDTO(Product product) {
        this.name = product.getTitle();
        this.price = product.getCurrentPrice();
        this.description = product.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
