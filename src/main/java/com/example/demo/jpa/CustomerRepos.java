package com.example.demo.jpa;

import com.example.demo.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepos extends JpaRepository<CustomerEntity,Integer> {
}
