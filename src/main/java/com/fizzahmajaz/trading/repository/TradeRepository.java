package com.fizzahmajaz.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fizzahmajaz.trading.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>{

    
}
