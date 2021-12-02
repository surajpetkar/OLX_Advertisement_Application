package com.zensar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService uds;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {  // To Override Default Authentication
	
		auth.userDetailsService(uds);
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("tom").password(getPasswordEncoder().encode("tom123")).roles(
		 * "USER") .and()
		 * .withUser("jerry").password(getPasswordEncoder().encode("jerry123")).roles(
		 * "ADMIN");
		 */
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {   // To Override Default Authorization
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/authenticate","/token/validate").permitAll()
		.and()
		.formLogin();
		/*.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/all","/authenticate").permitAll()
		.and()
		.formLogin();*/
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
