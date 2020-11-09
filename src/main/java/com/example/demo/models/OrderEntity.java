package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "public", catalog = "OnlineShop")
@ToString
@Getter @Setter @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "orderid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "destination")
    private String destination;
    @Basic
    @Column(name = "order_date")
    private String orderDate;
    @Basic
    @Column(name = "shipped_date")
    private String shippedDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerid", nullable = false)
    @JsonIgnore
    private CustomerEntity customer;

}
