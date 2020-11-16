/***
 * @author Daba Dashiev
 * This class represent interface of order which inherits methods from interface JpaRepository and has  my own specidied two methods
 */
package com.example.demo.jpa;
import com.example.demo.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepos extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findOrderEntityByCustomer_Customerid(Long customerid);

}
