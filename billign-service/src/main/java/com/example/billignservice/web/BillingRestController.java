package com.example.billignservice.web;


import com.example.billignservice.BillingServiceApplication;
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
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class BillingRestController {

    private BillRepository billRepository;
    private ProductItemRepository itemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository itemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.itemRepository = itemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable Long id)
    {

        Bill bill=billRepository.findById(id).get();
        Customer customer=customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(p->{
            Product product=productItemRestClient.getProductById(p.getProductID());
            p.setProduct(product);
            p.setProductName(product.getName());
        });
        System.out.println("2 Product items size : "+bill.getProductItems().size());
        return  bill;
    }

    @GetMapping(path = "/fullBill/search/{customerId}")
    public List<Bill> getBillsByCustomer(@PathVariable Long customerId)
    {
        List<Bill> bills=billRepository.findByCustomerID(customerId);
        List<Bill> billsList=new ArrayList<Bill>();
        List<ProductItem> productItems=new ArrayList<>() ;
       for (Bill bill:bills)
       {
           for (ProductItem productItem: itemRepository.findByBillId(bill.getId()))
           {
               productItem.setProduct(productItemRestClient.getProductById(productItem.getProductID()));
               productItem.setProductName(productItemRestClient.getProductById(productItem.getProductID()).getName());
               productItems.add(productItem);
           }
           bill.setProductItems(productItems);
           billsList.add(bill);
       }
        return  billsList;
    }

    @GetMapping(path = "/fullBill")
    public List<Bill> getAllBills(@PathVariable Long id)
    {
        List<Bill> bills=billRepository.findAll();
        List<Bill> bills2=new ArrayList<Bill>();
        for (Bill bill: bills) {
            Customer customer=customerRestClient.getCustomerById(bill.getCustomerID());
            bill.setCustomer(customer);
            bill.getProductItems().forEach(p->{
                Product product=productItemRestClient.getProductById(p.getProductID());
                p.setProduct(product);
                p.setProductName(product.getName());
            });
            bills2.add(bill);
        }
        return  bills2;
    }

    private String getToken()
    {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
        String token=kPrincipal.getKeycloakSecurityContext().getTokenString();
        return token;
    }

}
