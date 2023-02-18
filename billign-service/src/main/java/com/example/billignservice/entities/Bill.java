package com.example.billignservice.entities;


import com.example.billignservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    private Long id;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;
    private Long customerID;
    @Transient
    private Customer customer;
    public double getTotal()
    {
        double some=0;
        for (ProductItem item:productItems)
        { some=some+item.getAmount();
        }
       return some;
    }

}
