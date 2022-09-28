package com.ensah.authservice.services;

import com.ensah.authservice.entities.AppRole;
import com.ensah.authservice.entities.AppUser;

import java.util.List;

public interface AccountService {

	AppUser addUser(AppUser appUser) ;
	AppRole addRole(AppRole appRole) ;
	void addRoleToUser(String username , String rolename ) ;

	AppUser loadUserByUsername(String username) ;

	List<AppUser>  getUsers() ;

	List<AppRole> getRoles();
}
