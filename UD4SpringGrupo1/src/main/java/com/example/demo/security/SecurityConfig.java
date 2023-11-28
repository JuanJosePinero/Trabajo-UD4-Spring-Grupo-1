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
	                .requestMatchers("/", "/courses/**", "/imgs/**", "/auth/**", "/webjars/**", "/css/**", "/home/**", "/register" ,"/files/**"
	                ).permitAll()
	                .requestMatchers(
	                    "/admin/**"
	                ).hasRole("ADMIN")
	                .anyRequest().authenticated()
	        )
	        .formLogin(formLogin ->
	            formLogin
	                .loginPage("/login")
	                .defaultSuccessUrl("/courses/listCourses")
	                .permitAll()
	        )
	        .logout(logout ->
	            logout
	                .permitAll()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/auth/login?logout")
	        );
	    return http.build();
	}

    @Bean
     UserDetailsService userDetailsService() {
        UserDetails user = User
            .withUsername("user")
            .password("password")
            .roles("USER")
            .build();

        UserDetails admin = User
            .withUsername("admin")
            .password("adminpassword")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
