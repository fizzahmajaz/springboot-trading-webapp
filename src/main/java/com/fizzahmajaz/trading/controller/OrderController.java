package com.fizzahmajaz.trading.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzahmajaz.trading.entity.Order;
import com.fizzahmajaz.trading.entity.User;
import com.fizzahmajaz.trading.repository.OrderRepository;
import com.fizzahmajaz.trading.repository.UserRepository;

import lombok.AllArgsConstructor;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @PostMapping("/place/{userId}")
    public ResponseEntity<String> placeOrder(@PathVariable Long userId, @RequestBody Order order){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseEntity.badRequest().body("User not found");
        }
        
        order.setUser(user);
        order.setStatus(Order.OrderStatus.PENDING);
        orderRepository.save(order);

        return ResponseEntity.ok("Order Placed Sucessfully");

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()) return ResponseEntity.ok("No orders found");

        return ResponseEntity.ok(orders);
    }


    
}
