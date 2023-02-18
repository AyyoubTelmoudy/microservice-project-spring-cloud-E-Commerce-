
package com.randomorders.service;

import com.randomorders.entities.Bill;
import com.randomorders.entities.ProductItem;
import com.randomorders.feign.CustomerRestClient;
import com.randomorders.feign.ProductItemRestClient;
import com.randomorders.model.Customer;
import com.randomorders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
public class RandomOrderService {

    @Autowired
    CustomerRestClient customerRestClient;
    @Autowired
    ProductItemRestClient productItemRestClient;

    public Collection<ProductItem> productItems()
    {
        List<ProductItem> productItems=new ArrayList<ProductItem>();
        for (Product product:productItemRestClient.pageProducts().getContent().stream().collect(Collectors.toList())) {
            ProductItem productItem=new ProductItem((long) new Random().nextInt(9000),new Random().nextInt(9000),new Random().nextInt(9000),product.getId(),product.getName(),null,product);
            productItems.add(productItem);
        }
        return productItems;
    }


    @Bean
    public Supplier<Bill> billSupplier(){
        return ()-> {
            System.out.println("**********");
            double random=Math.random();
            int i=0;
            if(random<=0.3){i=0;}
            else {if(random<=0.6){i=1;}else {i=2;}}

                Customer customer=customerRestClient.getCustomers().getContent().stream().collect(Collectors.toList()).get(i);
                return new Bill((long) new Random().nextInt(9000),new Date(),productItems(), customer.getId(),customer);
        };
    }

}
