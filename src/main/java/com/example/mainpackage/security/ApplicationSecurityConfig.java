package com.example.mainpackage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //allows @PreAuthorize annotation to work in methods
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserServiceImpl1 applicationUserService;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserServiceImpl1 applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService= applicationUserService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			//.authorizeRequests()
			//.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
			//.anyRequest().authenticated()
			//.and()
			.formLogin()	//Use a login form as the authentication method
				.defaultSuccessUrl("/index.html", true)		//default redirect page after successful login
			.and().headers().frameOptions().sameOrigin();
		;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.daoAuthenticationProvider());
	}
	
	// Returns a custom DaoAuthenticationProvider
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(this.passwordEncoder);
		provider.setUserDetailsService(this.applicationUserService);
		
		return provider;
	}
	
}
