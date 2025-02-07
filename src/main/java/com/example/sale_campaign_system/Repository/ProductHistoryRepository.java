package com.example.sale_campaign_system.Repository;

import com.example.sale_campaign_system.Model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Integer> {
    @Query(value = "SELECT * FROM product_history WHERE product_id = :productId AND `date` = :date ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ProductHistory findTopByProductIdAndDate(@Param("productId") int productId, @Param("date") String date);

}
