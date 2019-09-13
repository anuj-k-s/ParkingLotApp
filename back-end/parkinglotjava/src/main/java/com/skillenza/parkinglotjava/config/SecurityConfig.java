package com.skillenza.parkinglotjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("anuj").password(passwordEncoder().encode("anuj")).roles("ADMIN")
			.and()
			.withUser("aks").password(passwordEncoder().encode("aks")).roles("USER")
			.and()
			.withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").hasRole("ADMIN")
			.antMatchers("/api").permitAll()
			.antMatchers("/api/api/parkings").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/home").authenticated()
			.and()
			.httpBasic();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}