package com.fizzahmajaz.trading.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fizzahmajaz.trading.entity.Order;
import com.fizzahmajaz.trading.entity.Order.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByStatus(OrderStatus status);

    
}
