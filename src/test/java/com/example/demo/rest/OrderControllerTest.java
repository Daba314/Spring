package com.example.demo.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(orderController).isNotNull();
    }

}