package com.example.sale_campaign_system.Controller;

import com.example.sale_campaign_system.Model.Product;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("saveProduct")
    public ResponseDTO<Product> saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

}
