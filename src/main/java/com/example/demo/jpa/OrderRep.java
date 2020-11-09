package com.example.demo.jpa;

import com.example.demo.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRep extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findOrderEntityByCustomer_Customerid(Long customerid);

}
