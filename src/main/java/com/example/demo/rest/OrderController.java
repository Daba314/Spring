package com.example.demo.rest;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.jpa.OrderRep;
import com.example.demo.models.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class OrderController {
    @Autowired
    private OrderRep orderRep;

    @Autowired
    private CustomerRepos customerRepos;

    @GetMapping("/customers/{customerId}/orders")
    public List<OrderEntity> getContactByCustomerId(@PathVariable Integer customerId) {

        if(!customerRepos.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }

        return orderRep.findByCustomerEntityId(customerId);
    }

}
