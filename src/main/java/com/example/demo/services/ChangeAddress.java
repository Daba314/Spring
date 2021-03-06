/***
 * @author Daba Dashiev
 * This class represent specific method for customer
 */
package com.example.demo.services;
import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.models.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChangeAddress {

    @Autowired
    private CustomerRepos customerRepos;

    /***
     * change selected customer address
     * @param id customer id
     * @param address customer address
     * @return  customer with changed address
     */
    @PostMapping("customers/{id}")
    public CustomerEntity changeAddressByCustomerId(@PathVariable Long id, @Validated @RequestBody String address) {
        return customerRepos.findById(id)
                .map(customerEntity -> {
                    customerEntity.setAddress(address);
                    return customerRepos.save(customerEntity);
                }).orElseThrow(() -> new NotFoundException("Customer not found with id " + id));


    }
}