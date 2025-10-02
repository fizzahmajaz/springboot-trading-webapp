package com.fizzahmajaz.trading.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fizzahmajaz.trading.entity.Order;
import com.fizzahmajaz.trading.entity.Portfolio;
import com.fizzahmajaz.trading.entity.Trade;
import com.fizzahmajaz.trading.entity.Order.OrderStatus;
import com.fizzahmajaz.trading.repository.OrderRepository;
import com.fizzahmajaz.trading.repository.PortfolioRepository;
import com.fizzahmajaz.trading.repository.TradeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class MatchingEngineService {

   public final OrderRepository orderRepository;
   public final TradeRepository tradeRepository;
   public final PortfolioRepository portfolioRepository;

    private void updatePortfolio(Order order, double tradeQuantity, double tradePrice){

      Portfolio portfolio = portfolioRepository.findByUserAndSymbol(order.getUser(), order.getSymbol());

      if(portfolio == null){
         portfolio = new Portfolio();
         portfolio.setUser(order.getUser());
         portfolio.setSymbol(order.getSymbol());
         portfolio.setQuantity(0.0);
         portfolio.setAvgPrice(0.0);
      }
      if(order.getType() == Order.OrderType.BUY){
         double totalCost = portfolio.getAvgPrice() * portfolio.getQuantity() + tradePrice * tradeQuantity;
         portfolio.setQuantity(portfolio.getQuantity() + tradeQuantity);
         portfolio.setAvgPrice(totalCost / portfolio.getQuantity());

      }else{ //if its selling
         portfolio.setQuantity(portfolio.getQuantity() - tradeQuantity);

      }

      portfolioRepository.save(portfolio);

   }

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
               updatePortfolio(buy, tradeQuantity, tradePrice);
               updatePortfolio(sell, tradeQuantity, tradePrice);

               break;
            }
         }
      }                                                                                                                                         
   }

  








   
}
