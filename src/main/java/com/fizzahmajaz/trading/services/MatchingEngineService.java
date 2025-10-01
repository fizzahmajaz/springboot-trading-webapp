package com.fizzahmajaz.trading.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fizzahmajaz.trading.entity.Order;
import com.fizzahmajaz.trading.entity.Order.OrderStatus;
import com.fizzahmajaz.trading.repository.OrderRepository;
import com.fizzahmajaz.trading.repository.TradeRepository;
import lombok.Data;

@Data
@Service
public class MatchingEngineService {

   public final OrderRepository orderRepository;
   public final TradeRepository tradeRepository;

   @Transactional
   public void matchOrder(){
      List<Order> buyOrder = orderRepository.findByStatus(OrderStatus.PENDING).stream()
                                                                              .filter(o->o.getType() == Order.OrderType.SELL)
                                                                              .toList();
      
                                                                              
      List<Order> sellOrder = orderRepository.findByStatus(OrderStatus.PENDING).stream()
                                                                               .filter(o->o.getType() == Order.OrderType.BUY)
                                                                               .toList();                                                                        
   }








   
}
