package com.example.cinemamng.repository;

import com.example.cinemamng.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IPromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT DISTINCT p FROM Promotion p WHERE p.startDate IS NOT NULL")
    List<Promotion> findDistinctPromotionsByStartDate();


    @Query("SELECT DISTINCT p FROM Promotion p WHERE p.endDate IS NOT NULL")
    List<Promotion> findDistinctPromotionsByEndDate();

    @Query("SELECT p FROM Promotion p WHERE " +
            "(:discount = 0 OR p.discount = :discount) " +
            "AND (:startDate IS NULL OR p.startDate = :startDate) " +
            "AND (:endDate IS NULL OR p.endDate = :endDate)")
    List<Promotion> searchPromotions(@Param ("discount") int discount,
                                     @Param ("startDate") String startDate,
                                     @Param("endDate") String endDate);


}
