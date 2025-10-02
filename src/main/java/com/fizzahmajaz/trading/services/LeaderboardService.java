package com.fizzahmajaz.trading.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fizzahmajaz.trading.dto.LeaderboardEntry;
import com.fizzahmajaz.trading.entity.Portfolio;
import com.fizzahmajaz.trading.entity.User;
import com.fizzahmajaz.trading.repository.PortfolioRepository;
import com.fizzahmajaz.trading.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class LeaderboardService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

    public List<LeaderboardEntry> getLeaderboard(){
        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        List<User> users = userRepository.findAll();
        for(User user : users){
            List<Portfolio> portfolios = portfolioRepository.findByUser(user);
            double pnl = portfolios.stream().mapToDouble(p -> p.getPnl()).sum();
            double totalBalance = user.getBalance() + pnl;
            leaderboard.add(new LeaderboardEntry(user.getUsername(), totalBalance));

        }

        leaderboard.sort((a, b) -> Double.compare(b.getTotalBalance(), a.getTotalBalance()));
        return leaderboard;

        
    }


    
}
