package com.example.mainpackage.security;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>{
	
	//List<ApplicationUser> findByLastName(String lastName);

	// Returns the user with that id
	ApplicationUser findById(long id);
	
	// Returns the user with that username
	ApplicationUser findByUsername(String username);
	
	// Inserts the user if user's id is null, updates otherwise
	<S extends ApplicationUser> S save(S applicationUser);
	
	// Returns true if row exists with username
	boolean existsByUsername(String username);
}

