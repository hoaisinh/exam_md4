package com.example.cinemamng.service;

import com.example.cinemamng.model.Promotion;
import com.example.cinemamng.repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    public IPromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> findDistinctStartDate() {
        return promotionRepository.findDistinctPromotionsByStartDate();
    }

    @Override
    public List<Promotion> findDistinctEndDate() {
        return promotionRepository.findDistinctPromotionsByEndDate();
    }

    @Override
    public List<Promotion> searchPromotions(int discount, String startDate, String endDate) {
        return promotionRepository.searchPromotions(discount, startDate, endDate);

    }

    @Override
    public void delete(Long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(()->new RuntimeException("Promotion not found"));
        promotionRepository.delete(promotion);
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }
}
