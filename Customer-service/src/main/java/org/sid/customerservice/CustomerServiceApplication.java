package org.sid.customerservice;

import org.sid.customerservice.Entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@Configuration
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            Customer customer=new Customer(null,"amine","amineyassir@gmail.com");
            Customer customer2=new Customer(null,"haoud","haoudyassir@gmail.com");
            Customer customer3=new Customer(null,"anas","anassyassir@gmail.com");
            customerRepository.save(customer);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.findAll().forEach(c->{
                System.out.println(c.getName());
            });
        };
    }
}
