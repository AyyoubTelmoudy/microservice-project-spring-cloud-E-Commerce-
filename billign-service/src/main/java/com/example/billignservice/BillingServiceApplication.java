package com.example.billignservice;

import com.example.billignservice.entities.Bill;
import com.example.billignservice.entities.ProductItem;
import com.example.billignservice.feign.CustomerRestClient;
import com.example.billignservice.feign.ProductItemRestClient;
import com.example.billignservice.model.Customer;
import com.example.billignservice.model.Product;
import com.example.billignservice.repositories.BillRepository;
import com.example.billignservice.repositories.ProductItemRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Autowired
    BillRepository billRepository;
    @Autowired
    ProductItemRepository productItemRepository;
    @Bean
    public Consumer<Bill> billConsumer()
    {
        return  (bill)->{
            Bill newBill=new Bill(bill.getId(),bill.getBillingDate(),null,bill.getCustomerID(),bill.getCustomer());
            billRepository.save(newBill);
            for (ProductItem productItem:bill.getProductItems())
            {
                ProductItem newProductItem=new ProductItem(productItem.getId(),productItem.getQuantity(),productItem.getPrice(),productItem.getProductID(),productItem.getProductName(),newBill,productItem.getProduct());
                productItemRepository.save(newProductItem);
            }
        };
    }
}
