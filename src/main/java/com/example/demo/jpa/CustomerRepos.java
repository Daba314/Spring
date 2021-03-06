/***
 * @author Daba Dashiev
 * This class represent interface of customer which inherits methods from interface JpaRepository and my own specidied two methods
 */
package com.example.demo.jpa;
import com.example.demo.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepos extends JpaRepository<CustomerEntity,Long> {
    CustomerEntity findByFirstnameAndLastname(String firstname, String lastName);
    boolean existsByFirstnameAndLastname(String firstname,String lastName);
}