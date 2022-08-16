package com.example.billingservice;

import com.example.billingservice.Entities.Bill;
import com.example.billingservice.Entities.ProductItems;
import com.example.billingservice.Feign.CustomerRestClient;
import com.example.billingservice.Feign.ProductItemRestClient;
import com.example.billingservice.Model.Customer;
import com.example.billingservice.Model.Product;
import com.example.billingservice.Repository.BillRepository;
import com.example.billingservice.Repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient){
        return args -> {
            Customer customer=customerRestClient.getCustomerById(1L);
            Bill bill1=billRepository.save(new Bill(null,new Date(),null, customer.getId(),null ));
            PagedModel<Product>products=productItemRestClient.PageProduct();
            products.forEach(p->{
                ProductItems productItems=new ProductItems();
                productItems.setPrix(p.getPrice());
                productItems.setProductID(p.getId());
                productItems.setQuantity(1+new Random().nextInt(100));
                productItems.setBill(bill1);
                productItemRepository.save(productItems);
            });
        };
    }

}
