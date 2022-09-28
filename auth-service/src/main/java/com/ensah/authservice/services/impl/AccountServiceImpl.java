package com.ensah.authservice.services.impl;

import com.ensah.authservice.entities.AppRole;
import com.ensah.authservice.entities.AppUser;
import com.ensah.authservice.repo.AppRoleRepository;
import com.ensah.authservice.repo.AppUserRepository;
import com.ensah.authservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	private AppRoleRepository roleRepository ;
	private AppUserRepository userRepository ;

	private PasswordEncoder passwordEncoder ;


	public AccountServiceImpl(AppRoleRepository roleRepository, AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}




	@Override
	public AppUser addUser(AppUser appUser) {

		// before storing the password we should encod it
		String encodedPassword = passwordEncoder.encode(appUser.getPassword()) ;
		appUser.setPassword(encodedPassword);
		return userRepository.save(appUser);
	}

	@Override
	public AppRole addRole(AppRole appRole) {
		return roleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		// find the user first , then add him a role second
		// bacause the service method is transactional then we can add the role to the user returned
		// and it will saved without a problem in the databsae , because the session is opened by default
		// open-session-in-view pattern

		AppUser user = userRepository.findByUsername(username) ;
		AppRole role = roleRepository.findByRoleName(rolename) ;

		user.getAppRoles().add(role) ;
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<AppRole> getRoles() {
		return roleRepository.findAll();
	}
}
