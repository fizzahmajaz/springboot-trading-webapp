package com.fizzahmajaz.trading.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzahmajaz.trading.dto.LeaderboardEntry;
import com.fizzahmajaz.trading.services.LeaderboardService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<?> getLeaderboard(){
        List<LeaderboardEntry> Leaderboard = leaderboardService.getLeaderboard();

        if(Leaderboard.isEmpty()) return ResponseEntity.ok("Leaderboard is empty");
        return ResponseEntity.ok(Leaderboard);
    }
    
}
