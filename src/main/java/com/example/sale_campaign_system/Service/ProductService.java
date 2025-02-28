package com.example.sale_campaign_system.Service;

import com.example.sale_campaign_system.Model.Product;
import com.example.sale_campaign_system.Model.ProductDTO;
import com.example.sale_campaign_system.Model.ProductHistory;
import com.example.sale_campaign_system.Model.ResponseDTO;
import com.example.sale_campaign_system.Repository.ProductHistoryRepository;
import com.example.sale_campaign_system.Repository.ProductRepository;


import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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




    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public ResponseDTO<org.springframework.data.domain.Page<ProductDTO>> findAllPaginated(int page, int size) {
        try {
            Pageable pageable =  PageRequest.of(page -1 ,size);
            Page<Product> productpage =productRepository.findAll(pageable);

            Page<ProductDTO> productDTOPage = productpage.map(ProductDTO ::new);
            return new ResponseDTO<>(productDTOPage,"your products",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseDTO<>(null,"not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseDTO<Product> updateProduct(int productId, double price) {
        try {
            Product product=  productRepository.findById(productId).orElse(null);
            if(product == null){
                return new ResponseDTO<>(product,"product not found",HttpStatus.NOT_FOUND);
            }
            if(product.getCurrentPrice()!=price){
                product.setCurrentPrice(price);
                productRepository.save(product);
                saveHistory(product);
            }

            return new ResponseDTO<>(product,"updated scussesfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseDTO<>(null,"faild to update product"+e.getMessage(),HttpStatus.BAD_REQUEST);
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
}
