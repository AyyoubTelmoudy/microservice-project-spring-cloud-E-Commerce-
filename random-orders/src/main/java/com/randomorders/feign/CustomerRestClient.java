package com.randomorders.feign;

import com.randomorders.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping(path = "/customers/{id}")
    public CustomerRestClient getCustomerById(@PathVariable Long id);

    @GetMapping(path = "/customers")
    public PagedModel<Customer> getCustomers();
}
