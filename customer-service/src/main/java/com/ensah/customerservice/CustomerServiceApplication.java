package com.ensah.customerservice;

import com.ensah.customerservice.bo.Customer;
import com.ensah.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerDao ;

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.save(new Customer(1L, "Jaouad", "jaouadjaouad@gmail.com"));
		customerDao.save(new Customer(2L, "Yassine", "jaouadyassine@gmail.com"));
		customerDao.save(new Customer(3L, "Mostafa", "jaouadmostafa@gmail.com"));


	}


	@Bean
	CommandLineRunner configuration(RepositoryRestConfiguration repositoryRestConfiguration){
		 repositoryRestConfiguration.exposeIdsFor(Customer.class);

		 return args -> {} ;
	}
}
