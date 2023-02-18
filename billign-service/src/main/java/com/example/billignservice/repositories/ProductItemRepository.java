package com.example.billignservice.repositories;

import com.example.billignservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    public List<ProductItem> findByBillId(Long billId);
}
