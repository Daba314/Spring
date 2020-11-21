/***
 * @author Daba Dashiev
 * This class represent specific method for customer
 */
package com.example.demo.services;
import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.models.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FindCustomer {
    @Autowired
    private CustomerRepos customerRepos;
    Logger logger = LoggerFactory.getLogger(FindCustomer.class);
    /***
     * Get customer with chosen first and last name
     * @param firstName customer first name
     * @param lastName customer last name
     * @return customer with selected first and last name
     */
    @GetMapping("/services/{firstname}/{lastname}")
    public Optional<CustomerEntity> getCustomerByFirstAndLastName(@PathVariable(name="firstname") String firstName,
                                                                  @PathVariable(name="lastname") String lastName)
            {

            logger.trace("getCustomerByFirstAndLastName accessed");

                return Optional.of(customerRepos.findByFirstnameAndLastname(firstName, lastName));


    }
}
