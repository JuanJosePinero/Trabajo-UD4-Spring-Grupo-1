package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
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
	                .requestMatchers("/", "/courses/**", "/imgs/**", "/error", 
	                		"/auth/**", "/webjars/**", "/css/**", "/home/**", "/files/**"
	                ).permitAll()
	                .requestMatchers(
	                    "/admin/**"
	                ).hasRole("ADMIN")
	                .anyRequest().authenticated()
	        )
	        .formLogin(formLogin ->
	            formLogin
	                .loginPage("/auth/login")
	                .defaultSuccessUrl("/home")
	                .failureHandler((request, response, exception) -> {
	                    String error = "unknownError";

	                    if (exception instanceof DisabledException) {
	                        error = "notActivated";
	                    } else if (exception instanceof BadCredentialsException) {
	                        error = "badCredentials";
	                    }

	                    
	                    response.sendRedirect("/auth/login?error=" + error);
	                    
	                })
	                .permitAll()
	        )
	        .logout(logout ->
            logout
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") 
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );
	    return http.build();
	}


}
