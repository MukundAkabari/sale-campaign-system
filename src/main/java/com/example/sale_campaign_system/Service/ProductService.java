package com.example.sale_campaign_system.Service;

import com.example.sale_campaign_system.Model.Product;
import com.example.sale_campaign_system.Model.ProductHistory;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Repository.ProductHistoryRepository;
import com.example.sale_campaign_system.Repository.ProductRepository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
   @Autowired
   ProductRepository productRepository;

   @Autowired
   ProductHistoryRepository productHistoryRepository;

    public ResponseDTO<Product> saveProduct(Product product) {
        try {
            if (product == null || product.getTitle() == null || product.getCurrentPrice() <= 0) {
                return new ResponseDTO<>(null, "Invalid product data. Title and price are required.", HttpStatus.BAD_REQUEST);
            }
            //save the product crete a instence of it
            Product savedProduct = productRepository.save(product);

            //auto maticaly save History
            saveHistory(savedProduct);

            return new ResponseDTO<>(savedProduct, "Product successfully saved.", HttpStatus.OK);

        } catch (DataAccessException e) {
            logger.error("Database error while saving product: {}", e.getMessage(), e);
            return new ResponseDTO<>(null, "Failed to save product due to database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Unexpected error while saving product: {}", e.getMessage(), e);
            return new ResponseDTO<>(null, "Failed to save product due to an unexpected error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public void saveHistory(Product product){
        try {
            //get the discount price
            double discount = product.getCurrentPrice() * (product.getDiscount() / 100.0);
            ProductHistory productHistory = new ProductHistory();
            productHistory.setProduct(product);
            productHistory.setPrice(product.getCurrentPrice());
            productHistory.setDisCountPrice(discount);
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
            productHistory.setLocalDate(LocalDate.now());
            //save the poductHistory
            productHistoryRepository.save(productHistory);
        }catch (Exception e){
            logger.error("Error daving History",e.getMessage());
        }
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }
}
