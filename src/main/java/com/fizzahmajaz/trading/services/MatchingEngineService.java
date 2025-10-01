package com.fizzahmajaz.trading.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fizzahmajaz.trading.entity.Order;
import com.fizzahmajaz.trading.entity.Trade;
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
      
      
      for(Order buy : buyOrder){
         for(Order sell : sellOrder){
            if(buy.getSymbol().equals(sell.getSymbol()) && buy.getPrice() >= sell.getPrice()){

               double tradeQuantity = Math.min(buy.getQuantity(), sell.getQuantity());
               double tradePrice = sell.getPrice();

               //create Trade
               Trade trade = new Trade();
               trade.setBuyOrder(buy);
               trade.setSellOrder(sell);
               trade.setSymbol(buy.getSymbol());
               trade.setPrice(tradePrice);
               trade.setQuantity(tradeQuantity);
               tradeRepository.save(trade);

               buy.setQuantity(buy.getQuantity() - tradeQuantity);
               sell.setQuantity(sell.getQuantity() - tradeQuantity);

               if(buy.getQuantity() == 0) buy.setStatus(OrderStatus.FILLED);
               if(sell.getQuantity() == 0) sell.setStatus(OrderStatus.FILLED);

               orderRepository.save(buy);
               orderRepository.save(sell);

               break;
            }
         }
      }                                                                                                                                         
   }








   
}
