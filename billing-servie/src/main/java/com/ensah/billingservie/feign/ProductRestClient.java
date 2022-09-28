package com.ensah.billingservie.feign;

import com.ensah.billingservie.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {

	@GetMapping("/products")
	 PagedModel<Product> findPageOfProducts(@RequestParam(name = "page") int page,
												  @RequestParam(name = "size") int size);
	@GetMapping("/products/{id}")
	Product getProductById(@PathVariable("id") Long productId) ;
}
