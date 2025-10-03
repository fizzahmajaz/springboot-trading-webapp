package com.fizzahmajaz.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class OrderBookEntry {

    private String type;
    private Double price;
    private Double quantity;


    
}
