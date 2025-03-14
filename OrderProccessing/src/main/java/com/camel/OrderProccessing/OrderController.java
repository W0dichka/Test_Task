package com.camel.OrderProccessing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping(consumes = "application/xml")
    public String receiveOrder(@RequestBody Order order) {
        orderRepository.addOrder(order);
        System.out.println("Получен заказ: " + order); 
        return "Заказ успешно получен!";
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }
}