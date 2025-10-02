package com.fizzahmajaz.trading.dto;

public class LeaderboardEntry {
    private String username;
    private Double totalBalance;

    public LeaderboardEntry(String username, Double totalBalance) {
        this.username = username;
        this.totalBalance = totalBalance;
    }

    public String getUsername() {
        return username;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    
}
