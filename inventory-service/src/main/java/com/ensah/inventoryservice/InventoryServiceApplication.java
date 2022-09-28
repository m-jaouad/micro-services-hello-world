package com.ensah.inventoryservice;

import com.ensah.inventoryservice.bo.Product;
import com.ensah.inventoryservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {
	@Autowired
	ProductRepository productRepository ;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productRepository.save(new Product(1L, "Computer", 123.5, 10) );
		productRepository.save(new Product(2L, "Mouse", 12.5, 10) );
		productRepository.save(new Product(3L, "Monitor", 23.5, 10) );

		List<Product> products = productRepository.findAll() ;

		for(Product product: products){
			System.out.println(product.getName() + product.getQuantity() ) ;
		}

	}


	@Bean
	CommandLineRunner configuration(RepositoryRestConfiguration repositoryRestConfiguration){
		repositoryRestConfiguration.exposeIdsFor(Product.class);

		return args -> {} ;
	}
}
