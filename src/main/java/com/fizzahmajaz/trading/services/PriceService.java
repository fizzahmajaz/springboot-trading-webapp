package com.fizzahmajaz.trading.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fizzahmajaz.trading.entity.Price;
import com.fizzahmajaz.trading.repository.PriceRepository;
import lombok.Data;

@Data
@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final WebSocketService webSocketService;

    private Map<String, Double> symbolPrice = new HashMap<>();
    private Random random = new Random();

    public PriceService(PriceRepository priceRepository, WebSocketService webSocketService) {
        this.priceRepository = priceRepository;
        this.webSocketService = webSocketService;

        symbolPrice.put("BTC", 30000.0);
        symbolPrice.put("ETH", 2000.0);
    }

    @Scheduled(fixedRate = 3000) //every 3 seconds
    public void simulatePrice(){
        symbolPrice.forEach((symbol, price) -> {double changePercent = (random.nextDouble() - 0.5) *0.02;
        double newPrice = price + price * changePercent;
        symbolPrice.put(symbol, newPrice);

        Price priceEntity = new Price();
        priceEntity.setSymbol(symbol);
        priceEntity.setPrice(newPrice);
        priceRepository.save(priceEntity);

        webSocketService.broadcastPrice(symbol, newPrice);

    });

    }


   
    
}
