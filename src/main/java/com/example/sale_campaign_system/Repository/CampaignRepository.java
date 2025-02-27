package com.example.sale_campaign_system.Repository;

import com.example.sale_campaign_system.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Integer> {
    @Query(value = "SELECT * FROM campaign WHERE start_date = :startDate", nativeQuery = true)
    List<Campaign> selectCampaignAllToday(@Param("startDate") LocalDate startDate);


    @Query(value = "SELECT * FROM campaign WHERE end_date= :endtDate",nativeQuery = true)
    List<Campaign> endcampaignalltoday(@Param("endtDate")LocalDate endDate);
}
