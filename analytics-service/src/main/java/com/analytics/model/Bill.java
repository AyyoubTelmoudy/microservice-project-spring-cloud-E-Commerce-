package com.analytics.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bill {
    private Long id;
    private Date billingDate;
    private Collection<ProductItem> productItems;
    private Long customerID;
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
