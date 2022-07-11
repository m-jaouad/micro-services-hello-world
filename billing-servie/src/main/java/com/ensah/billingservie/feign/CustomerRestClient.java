package com.ensah.billingservie.feign;

import com.ensah.billingservie.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
	/**
	 * une methode qui permet de se connecter au micro-service Customer et retourner
	 * le customer qui correspand au id inddiqué
	 * @param id du product
	 * @return customer corespond au id
	 */
	@GetMapping("/customers/{id}")
	Customer findCustomerById(@PathVariable(name = "id") Long id ) ;




}
