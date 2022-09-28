package com.ensah.billingservie;

import com.ensah.billingservie.bo.Bill;
import com.ensah.billingservie.bo.ProductItem;
import com.ensah.billingservie.feign.CustomerRestClient;
import com.ensah.billingservie.feign.ProductRestClient;
import com.ensah.billingservie.models.Customer;
import com.ensah.billingservie.models.Product;
import com.ensah.billingservie.repositories.BillRepository;
import com.ensah.billingservie.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

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
		// trouver un client puis , creer une facture pour tous les produicts dans une page
		Customer customer = customerRestClient.findCustomerById(1L) ;

		Bill bill = new Bill();
		bill.setDateBilling(new Date());
		bill.setCustomer(customer);
		bill.setCustomerId(customer.getId());

		billRepository.save(bill);

		PagedModel<Product> products = productRestClient.findPageOfProducts(0, 4);


		products.forEach(p ->{
			ProductItem productItem = new ProductItem() ;
			productItem.setProductId(p.getId());
			productItem.setBill(bill);
			productItem.setPrice(p.getPrice());
			productItem.setQuantity(new Random().nextInt(100));
			productItemRepository.save(productItem) ;

			System.out.println(p.getName());
		});

	}
}
