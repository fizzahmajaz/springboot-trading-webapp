package com.fizzahmajaz.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fizzahmajaz.trading.entity.Portfolio;
import com.fizzahmajaz.trading.entity.User;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

    List<Portfolio> findByUser(User user);
    Portfolio findByUserAndSymbol(User user, String symbol);

    
}