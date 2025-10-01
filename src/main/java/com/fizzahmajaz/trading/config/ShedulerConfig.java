package com.fizzahmajaz.trading.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fizzahmajaz.trading.services.MatchingEngineService;
import lombok.Data;

@Component
@EnableScheduling
@Data
public class ShedulerConfig {
    private final MatchingEngineService matchingEngineService;

    @Scheduled(fixedRate = 5000)
    public void runMatchingEngineService(){
        matchingEngineService.matchOrder();
    }

    
}
