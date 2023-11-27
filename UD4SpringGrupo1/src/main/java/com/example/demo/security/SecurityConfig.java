package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

	
//	Para que sirva para cualquier role que yo le meta
//	http.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/courses/**", "/imgs/**", "/auth/**",
//			"/webjars/**", "/css/**", "/files/**").permitAll().anyRequest().hasRole("cualquierRole").authenticated()
//		)
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authentificationConfiguration) throws Exception {
		return authentificationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorizeRequests ->
	            authorizeRequests
	                .requestMatchers("/", "/courses/**", "/imgs/**", "/auth/**", "/webjars/**", "/css/**", "/files/**"
	                ).permitAll()
	                .requestMatchers(
	                    "/admin/**"
	                ).hasRole("ADMIN")
	                .anyRequest().authenticated()
	        )
	        .formLogin(formLogin ->
	            formLogin
	                .loginPage("/auth/login")
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
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("adminpassword")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
