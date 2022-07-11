package com.ensah.billingservie.repositories;

import com.ensah.billingservie.bo.Bill;
import com.ensah.billingservie.bo.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
	public Collection<Bill> findByBillId(Long billId) ;

}
