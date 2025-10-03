package com.fizzahmajaz.trading.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fizzahmajaz.trading.dto.OrderBookEntry;
import com.fizzahmajaz.trading.entity.Order;
import com.fizzahmajaz.trading.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class OrderBookService {

    private final OrderRepository orderRepository;

    public List<OrderBookEntry> getOrderBook(String symbol){
        List<OrderBookEntry> entries = new ArrayList<>();

        List<Order> buyOrders = orderRepository.findBySymbol

    }

    
}
