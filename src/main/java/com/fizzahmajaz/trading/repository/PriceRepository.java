package com.fizzahmajaz.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fizzahmajaz.trading.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long>{
    Price findTopBySymbolOrderByTimeStampDesc(String symbol);

    
}