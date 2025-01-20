package com.example.cinemamng.service;

import com.example.cinemamng.model.Promotion;

import java.util.List;

public interface IPromotionService {
    List<Promotion> findAll();
    List<Promotion> findDistinctStartDate();
    List<Promotion> findDistinctEndDate();
    List<Promotion> searchPromotions(int discount, String startDate, String endDate);
    void delete(Long id);
    void save(Promotion promotion);

}
