package com.ensah.billingservie;

import com.ensah.billingservie.bo.ProductItem;
import com.ensah.billingservie.feign.CustomerRestClient;
import com.ensah.billingservie.feign.ProductRestClient;
import com.ensah.billingservie.models.Customer;
import com.ensah.billingservie.repositories.BillRepository;
import com.ensah.billingservie.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillingServieApplication implements CommandLineRunner {

	@Autowired
	CustomerRestClient customerRestClient ;

	@Autowired
	ProductRestClient productRestClient ;

	@Autowired
	BillRepository billRepository ;

	@Autowired
	ProductItemRepository productItemRepository ;

	public static void main(String[] args) {
		SpringApplication.run(BillingServieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = customerRestClient.findCustomerById(1L) ;

		System.out.println(customer.getName());
		System.out.println(customer.getId());
		System.out.println(customer.getEmail());
	}
}
