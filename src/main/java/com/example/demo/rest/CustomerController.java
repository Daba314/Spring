/***
 * @author Daba Dashiev
 * This class represent Spring REST for customer
 */
package com.example.demo.rest;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.models.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepos customerRepos;

    /***
     * Get list of all customers
     * @return list of all customers
     */
    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers() {
        logger.trace("getAllCustomers accessed");
        return  customerRepos.findAll();
    }

    /***
     * Get customer with chosen id
     * @param id customer id
     * @return customer with chosen id
     */
    @GetMapping("/customers/{id}")
    public CustomerEntity getCustomertByID(@PathVariable Long id) {
        Optional<CustomerEntity> optCust = customerRepos.findById(id);
        if(optCust.isPresent()) {
            return optCust.get();
        }else {
            throw new NotFoundException("Customer not found with id " + id);
        }
    }

    /***
     * Create new customer
     * @param customerEntity oject of CustomerEntity
     * @return new customer
     */
    @PostMapping("/customers")
    public CustomerEntity createCustomer(@Validated @RequestBody CustomerEntity customerEntity) {
        return customerRepos.save(customerEntity);
    }

    /***
     * Update customerEntity by customer id
     * @param id customer id
     * @param customerUpdated object of CustomerEntity
     * @return updated customer
     */
    @PutMapping("/customers/{id}")
    public CustomerEntity updateCustomer(@PathVariable Long id,
                                 @Validated @RequestBody CustomerEntity customerUpdated) {
        return customerRepos.findById(id)
                .map(customerEntity -> {
                    customerEntity.setFirstname(customerUpdated.getFirstname());
                    customerEntity.setLastname(customerUpdated.getLastname());
                    customerEntity.setPhonenumber(customerUpdated.getPhonenumber());
                    customerEntity.setAddress(customerUpdated.getAddress());
                    return customerRepos.save(customerEntity);
                }).orElseThrow(() -> new NotFoundException("Customer not found with id " + id));
    }

    /***
     * Delete customer by selected id
     * @param id customer id
     * @return message that customer was deleted
     */
    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerRepos.findById(id)
                .map(customerEntity -> {
                    customerRepos.delete(customerEntity);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Customer not found with id " + id));
    }
}
