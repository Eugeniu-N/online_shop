package com.example.mainpackage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl1 implements UserDetailsService{
	
	private final ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	public ApplicationUserServiceImpl1(ApplicationUserRepository applicationUserRepository) {
		super();
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserRepository.findByUsername(username);
	}
	
	public Iterable<? extends UserDetails> getAllApplicationUsers(){
		return applicationUserRepository.findAll();
	}
	
	public ApplicationUser save(ApplicationUser applicationUser) {
		return applicationUserRepository.save(applicationUser);
	}

}
