package com.fizzahmajaz.trading.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="buy_order_id")
    private Order buyOrder;

    @ManyToOne
    @JoinColumn(name="sell_order_id")
    private Order sellOrder;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double quantity;

    private LocalDateTime createdAt = LocalDateTime.now();


    
}
