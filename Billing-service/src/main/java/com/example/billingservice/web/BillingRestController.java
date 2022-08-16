package com.example.billingservice.web;

import com.example.billingservice.Entities.Bill;
import com.example.billingservice.Feign.CustomerRestClient;
import com.example.billingservice.Feign.ProductItemRestClient;
import com.example.billingservice.Model.Customer;
import com.example.billingservice.Model.Product;
import com.example.billingservice.Repository.BillRepository;
import com.example.billingservice.Repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository,
                                 ProductItemRepository productItemRepository,
                                 CustomerRestClient customerRestClient,
                                 ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/bill/full/{id}")
    public Bill getBill(@PathVariable Long id){
      Bill bill = billRepository.findById(id).get();
      Customer customer=customerRestClient.getCustomerById(bill.getCustomerID());
      bill.setCustomer(customer);
      bill.getProductItems().forEach(pi->{
          Product product=productItemRestClient.getProductById(pi.getProductID());
          pi.setProduct(product);
      });
      return bill;
    }
}
