package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authentificationConfiguration) throws Exception {
		return authentificationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorizeRequests ->
	            authorizeRequests
	                .requestMatchers("/", "/courses/**", "/imgs/**", "/admin/**", "/error", 
	                		"/auth/**", "/webjars/**", "/css/**", "/home/**", "/files/**"
	                ).permitAll()
	                .requestMatchers(
	                    "/admin/**"
	                ).hasRole("a")
	                .anyRequest().authenticated()
	        )
	        .formLogin(formLogin ->
	            formLogin
	                .loginPage("/auth/login")
	                .defaultSuccessUrl("/admin/adminScreen")
	                .permitAll()
	        )
	        .logout(logout ->
	            logout
	                .permitAll()
	                .logoutUrl("/auth/logout")
	                .logoutSuccessUrl("/auth/login?logout")
	        );
	    System.out.println("Hola desde security config");
	    return http.build();
	}


}
