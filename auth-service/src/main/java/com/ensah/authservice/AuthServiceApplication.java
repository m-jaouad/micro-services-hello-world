package com.ensah.authservice;

import com.ensah.authservice.entities.AppRole;
import com.ensah.authservice.entities.AppUser;
import com.ensah.authservice.repo.AppRoleRepository;
import com.ensah.authservice.repo.AppUserRepository;
import com.ensah.authservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServiceApplication  {


	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(@Lazy AccountService accountService){

		return args -> {


			// création des users
			AppUser appUser = new AppUser();
			appUser.setUsername("jaouad");
			appUser.setPassword("goodMorning");

			AppUser user1 = new AppUser();
			user1.setUsername("mostafa");
			user1.setPassword("goodMorning");

			AppUser user2 = new AppUser();
			user2.setUsername("nabila");
			user2.setPassword("goodMorning");


			// creation des roles
			AppRole admin = new AppRole();
			admin.setRoleName("ADMIN");

			AppRole user = new AppRole();
			user.setRoleName("USER");

			AppRole manager = new AppRole();
			manager.setRoleName("MANAGER");

			// save the users
			accountService.addUser(appUser);
			accountService.addUser(user1);
			accountService.addUser(user2);

			// save the roles
			accountService.addRole(user);
			accountService.addRole(admin);
			accountService.addRole(manager);

			// adding roles to the users
			accountService.addRoleToUser("jaouad", "ADMIN");
			accountService.addRoleToUser("jaouad", "MANAGER");
			accountService.addRoleToUser("jaouad", "USER");

			accountService.addRoleToUser("mostafa", "USER");

			accountService.addRoleToUser("nabila", "MANAGER");

		};


	}


}
