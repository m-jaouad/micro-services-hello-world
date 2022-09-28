package com.ensah.authservice.web;


import com.ensah.authservice.entities.AppRole;
import com.ensah.authservice.entities.AppUser;
import com.ensah.authservice.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
//@RequestMapping("/admin")
public class AccountRestAccount {

	private AccountService accountService ;

	public AccountRestAccount(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/users")
	public List<AppUser> getUsers(){
		return accountService.getUsers() ;
	}

	@PostMapping("/users")
	public AppUser saveUser(@RequestBody AppUser appUser){
		return  accountService.addUser(appUser) ;
	}


	@PostMapping("/roles")
	public AppRole saveRole(@RequestBody AppRole appRole){
		return  accountService.addRole(appRole) ;
	}

	@PostMapping("/addRoleToUser")
	public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
		  accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRole());
	}


	@GetMapping("/roles")
	public  List<AppRole> getRoles(){
		return accountService.getRoles() ;
	}

	@GetMapping("/profile")
	public  AppUser getProfile(Principal principal){
		return accountService.loadUserByUsername(principal.getName()) ;
	}

}


