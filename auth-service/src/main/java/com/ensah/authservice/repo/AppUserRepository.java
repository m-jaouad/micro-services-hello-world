package com.ensah.authservice.repo;

import com.ensah.authservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username) ;
}