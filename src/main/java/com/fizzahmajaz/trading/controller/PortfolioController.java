package com.fizzahmajaz.trading.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzahmajaz.trading.entity.Portfolio;
import com.fizzahmajaz.trading.entity.User;
import com.fizzahmajaz.trading.repository.PortfolioRepository;
import com.fizzahmajaz.trading.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getPortfolio(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) return ResponseEntity.badRequest().body("User not Found");

        List<Portfolio> portfolio = portfolioRepository.findByUser(user);
        
        if(portfolio.isEmpty()) return ResponseEntity.ok("portfolio is empty");

        return ResponseEntity.ok(portfolio);
    }
    
}
