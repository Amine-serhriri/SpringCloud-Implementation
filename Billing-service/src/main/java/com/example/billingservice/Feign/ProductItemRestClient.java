package com.example.billingservice.Feign;

import com.example.billingservice.Model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface  ProductItemRestClient {
    @GetMapping(path = "/products")
    PagedModel<Product> PageProduct(@RequestParam(name = "page") int page ,
                                    @RequestParam(name = "size") int size);
    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);
    @GetMapping(path = "/products")
    PagedModel<Product> PageProduct();
}
