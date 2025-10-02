package com.fizzahmajaz.trading.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class WebSocketService {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    public void addSession(WebSocketSession session){
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session){
        sessions.remove(session);
    }
    

    public static class PriceMessage{
        public String symbol;
        public Double price;

        public PriceMessage(String symbol, Double price) {
            this.symbol = symbol;
            this.price = price;
        }
        
    }


    public void broadcastPrice(String symbol, Double price){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(new PriceMessage(symbol, price));
            TextMessage textMessage = new TextMessage(msg);
            sessions.forEach(session -> {
                try {
                    session.sendMessage(textMessage);
                } catch (Exception ignored) {
                    
                }
            });
        }catch(Exception ignored) {}
    }

    


}
