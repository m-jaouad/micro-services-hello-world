package com.ensah.billingservie.web;


import com.ensah.billingservie.bo.Bill;
import com.ensah.billingservie.feign.CustomerRestClient;
import com.ensah.billingservie.feign.ProductRestClient;
import com.ensah.billingservie.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestBillingController {

	@Autowired
	BillRepository billRepository ;
	@Autowired

	CustomerRestClient customerRestClient ;
	@Autowired

	ProductRestClient productRestClient ;



	@GetMapping("/fullBill/{id}")
	public Bill getFullBilling(@PathVariable(name = "id") Long id){
		Bill bill = billRepository.findById(id).get() ;
		bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
		bill.getProductItems().forEach(pi ->{
			pi.setProduct(productRestClient.getProductById(pi.getProductId()));
		});
		return bill ;
	}
}
