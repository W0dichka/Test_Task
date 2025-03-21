package com.camel.OrderProccessing;

public class Item { 
    private String name;
	
    private double price;
	
    @Override
    public String toString() {
        return "Item{" +
               "name='" + name + '\'' +
               ", price=" + price +
               '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}