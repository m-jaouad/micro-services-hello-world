package com.ensah.inventoryservice.repositories;

import com.ensah.inventoryservice.bo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
}
