package com.example.billingservice.Entities;

import com.example.billingservice.Model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date billingDate ;
    @OneToMany(mappedBy = "bill")

    private Collection<ProductItems>productItems;
    private long customerID;

    @Transient
    private Customer customer;

}
