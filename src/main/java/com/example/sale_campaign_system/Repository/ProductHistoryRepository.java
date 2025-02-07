package com.example.sale_campaign_system.Repository;

import com.example.sale_campaign_system.Model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Integer> {
}
