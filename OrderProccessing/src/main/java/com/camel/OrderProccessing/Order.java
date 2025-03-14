package com.camel.OrderProccessing;

import java.util.List;

public class Order {
	
    private int orderId; 
	
    private String customer;
	
    private List<Item> items;  

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    sb.append("Order{")
	    .append("orderId=").append(orderId)
	    .append(", customer='").append(customer).append('\'')
	    .append(", items=").append(items)
	    .append('}');
	    return sb.toString();
	}

    public int getOrderId() {  
        return orderId;
    }

    public void setOrderId(int orderId) {  
        this.orderId = orderId;
    }
    
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
