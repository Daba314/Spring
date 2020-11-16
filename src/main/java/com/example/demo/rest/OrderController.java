/***
 * @author Daba Dashiev
 * This class represent Spring REST for order
 */
package com.example.demo.rest;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.jpa.OrderRepos;
import com.example.demo.models.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderRepos orderRep;

    @Autowired
    private CustomerRepos customerRepos;

    /***
     * Get list of orders by selected customer id
     * @param customerId customer id
     * @return list of orders by selected customer id
     */
    @GetMapping("/customers/{customerId}/orders")
    public List<OrderEntity> getContactByCustomerId(@PathVariable Long customerId) {

        if(!customerRepos.existsById(customerId)) {
            throw new NotFoundException("Customer not found!");
        }

        return orderRep.findOrderEntityByCustomer_Customerid(customerId);
    }

    /***
     * Create new order for selected customer
     * @param customerId customer id
     * @param orderEntity object of OrderEntity
     * @return new order
     */
    @PostMapping("/customers/{customerId}/orders")
    public OrderEntity addOrder(@PathVariable Long customerId,
                                    @Validated @RequestBody OrderEntity orderEntity) {
        return customerRepos.findById(customerId)
                .map(customerEntity -> {
                    orderEntity.setCustomer(customerEntity);
                    return orderRep.save(orderEntity);
                }).orElseThrow(() -> new NotFoundException("Customer not found!"));
    }

    /***
     * Update order parameters by selected customer id
     * @param customerId customer id
     * @param orderId order id
     * @param orderUpdated object of OrderEntity
     * @return saved order changes
     */
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

    /***
     * Delete order by its id and customer id
     * @param customerId customer id
     * @param orderId order id
     * @return message about successful deletion
     */
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