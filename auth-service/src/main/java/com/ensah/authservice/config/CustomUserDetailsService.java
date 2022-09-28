package com.ensah.authservice.config;

import com.ensah.authservice.entities.AppUser;
import com.ensah.authservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;


public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountService accountService ;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser appUser = accountService.loadUserByUsername(username);

		// create a collection of granteted authorities
		Collection<GrantedAuthority> roles = new ArrayList<>();

		appUser.getAppRoles().forEach(role -> {
			roles.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		return new User(appUser.getUsername(), appUser.getPassword(), roles);
	}
}

