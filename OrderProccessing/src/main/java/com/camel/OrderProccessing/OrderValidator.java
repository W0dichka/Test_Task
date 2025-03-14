package com.camel.OrderProccessing;

import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    public boolean hasInvalidPrices(Order order) {
    	
        return order.getItems().stream().anyMatch(item -> item.getPrice() <= 0);
    }
    
    public boolean hasEmptyItemNames(Order order) {
        return order.getItems().stream().anyMatch(item -> item.getName() == null || item.getName().trim().isEmpty());
    }
}