package com.example.demo.rest;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.jpa.OrderRep;
import com.example.demo.models.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderRep orderRep;

    @Autowired
    private CustomerRepos customerRepos;

    @GetMapping("/customers/{customerId}/orders")
    public List<OrderEntity> getContactByCustomerId(@PathVariable Long customerId) {

        if(!customerRepos.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }

        return orderRep.findOrderEntityByCustomer_Customerid(customerId);
    }
    @PostMapping("/customers/{customerId}/orders")
    public OrderEntity addOrder(@PathVariable Long customerId,
                                    @Validated @RequestBody OrderEntity orderEntity) {
        return customerRepos.findById(customerId)
                .map(customerEntity -> {
                    orderEntity.setCustomer(customerEntity);
                    return orderRep.save(orderEntity);
                }).orElseThrow(() -> new NotFoundException("Customer not found!"));
    }

    @PutMapping("/customers/{customerId}/orders/{orderId}")
    public OrderEntity updateOrder(@PathVariable Long customerId,
                                       @PathVariable Long orderId,
                                       @Validated @RequestBody OrderEntity orderUpdated) {

        if(!customerRepos.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }

        return orderRep.findById(orderId)
                .map(orderEntity -> {
                    orderEntity.setStatus(orderUpdated.getStatus());
                    orderEntity.setDestination(orderUpdated.getDestination());
                    orderEntity.setOrderDate(orderUpdated.getOrderDate());
                    orderEntity.setShippedDate(orderUpdated.getShippedDate());
                    return orderRep.save(orderEntity);
                }).orElseThrow(() -> new NotFoundException("Order not found!"));
    }

    @DeleteMapping("/customers/{customerId}/orders/{orderId}")
    public String deleteOrder(@PathVariable Long customerId,
                                   @PathVariable Long orderId) {

        if(!customerRepos.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }

        return orderRep.findById(orderId)
                .map(orderEntity -> {
                    orderRep.delete(orderEntity);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }
}