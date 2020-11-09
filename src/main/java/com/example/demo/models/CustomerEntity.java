package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customers", schema = "public", catalog = "Shop")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@Getter @Setter @NoArgsConstructor
public class CustomerEntity  {
    @Id
    @Column(name = "customerid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerid;

    @Basic
    @Column(name = "firstname")
    private String firstname;

    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "phonenumber")
    private String phonenumber;
    @Basic
    @Column(name = "address")
    private String address;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "customer")
    private Set<OrderEntity> orders;

}
