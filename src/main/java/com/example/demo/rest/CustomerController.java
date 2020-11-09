package com.example.demo.rest;

import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.models.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepos customerRepos;

    @GetMapping("/customers")
    public List<CustomerEntity> getAllStudents() {
        return  customerRepos.findAll();
    }
    @GetMapping("/customers/{id}")
    public CustomerEntity getCustomertByID(@PathVariable Integer id) {
        Optional<CustomerEntity> optCust = customerRepos.findById(id);
        if(optCust.isPresent()) {
            return optCust.get();
        }else {
            throw new NotFoundException("Customer not found with id " + id);
        }
    }

    @PostMapping("/customers")
    public CustomerEntity createCustomer(@Validated @RequestBody CustomerEntity customerEntity) {
        return customerRepos.save(customerEntity);
    }

    @PutMapping("/customers/{id}")
    public CustomerEntity updateStudent(@PathVariable Integer id,
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

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        return customerRepos.findById(id)
                .map(customerEntity -> {
                    customerRepos.delete(customerEntity);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Customer not found with id " + id));
    }
}
