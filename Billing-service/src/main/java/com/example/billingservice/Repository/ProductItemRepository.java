package com.example.billingservice.Repository;

import com.example.billingservice.Entities.Bill;
import com.example.billingservice.Entities.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItems,Long> {
    public Collection<ProductItems>findByBillId(Long id);
}
