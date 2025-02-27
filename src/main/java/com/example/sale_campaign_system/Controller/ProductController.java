package com.example.sale_campaign_system.Controller;

import com.example.sale_campaign_system.Model.Product;
import com.example.sale_campaign_system.Model.ProductDTO;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Service.ProductService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("saveProduct")
    public ResponseDTO<Product> saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @GetMapping("getProduct")
    public List<ProductDTO> getProduct(){
        return productService.getProduct()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toUnmodifiableList());
    }

}
