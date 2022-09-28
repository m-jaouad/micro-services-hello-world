package com.ensah.authservice.config;


import com.ensah.authservice.entities.AppUser;
import com.ensah.authservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
//	@Lazy
//	@Autowired
//	private AccountService accountService;

	@Bean
	UserDetailsService getUserDetailsService(){
		return new CustomUserDetailsService() ;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable().and()
				//http.formLogin() ;

				.authorizeRequests().antMatchers("/h2-console/**").permitAll().and()

				.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN").and() // pour le role admin

				.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority("USER").and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()


				.authorizeRequests().anyRequest().authenticated();

		http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				AppUser appUser = accountService.loadUserByUsername(username);
//
//				// create a collection of granteted authorities
//				Collection<GrantedAuthority> roles = new ArrayList<>();
//
//				appUser.getAppRoles().forEach(role -> {
//					roles.add(new SimpleGrantedAuthority(role.getRoleName()));
//				});
//				return new User(appUser.getUsername(), appUser.getPassword(), roles);
//			}
//		});

		auth.userDetailsService(getUserDetailsService()) ;
	}
}
