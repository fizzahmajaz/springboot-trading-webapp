package com.fizzahmajaz.trading.services;

import java.util.HashMap;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fizzahmajaz.trading.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final WebSocketService webSocketService;

    private Map<String, Double> symbolPrice = new HashMap<>();
    private Random random = new Random();

    public PriceSimulatorService(PriceRepository priceRepository, WebSocketService webSocketService) {
        this.priceRepository = priceRepository;
        this.webSocketService = webSocketService;

        symbolPrices.put("BTC", 30000.0);
        symbolPrices.put("ETH", 2000.0);
    }

    @Scheduled(fixedRate = 3000) //every 3 seconds
    public void simulatePrice(){
        symbolPrices.forEach
    }


   
    
}
