 package com.example.demo.secutity;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

    	JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    	
    	theUserDetailsManager
    		.setUsersByUsernameQuery("SELECT username, password, enabled FROM Login WHERE username=?");
    	
    	theUserDetailsManager
    		.setAuthoritiesByUsernameQuery("SELECT username, authority FROM Authorities WHERE username=?");
    	
    	
    	
        return theUserDetailsManager;
    }
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	return http
    					.authorizeHttpRequests(configurer ->
                        	configurer
                        		
                        		.requestMatchers("/employees/emp-list").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/employees/emp-leave").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/employees/clients-list").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/employees/emp-department").hasAnyRole("MANAGER", "ADMIN")
                                
                                .requestMatchers("/emp-department-add").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/add-clients").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/projects-add").hasAnyRole("MANAGER", "ADMIN")
                                .anyRequest().authenticated()
                		)

                        .formLogin(form ->
                                form
                                        .loginPage("/login")
                                        .loginProcessingUrl("/authenticateTheUser")
                                        .defaultSuccessUrl("/dashboard", true)
                                        .permitAll()
                        )
//                        .sessionManagement(session -> session
//                                .maximumSessions(1)
//                                .maxSessionsPreventsLogin(true)
//                            )
                        .logout(logout -> logout.permitAll()
                        )
                        
	//                .exceptionHandling(configurer ->
	//                				configurer.accessDeniedPage("/access-denied")
	//                );
                        
                    .exceptionHandling(exceptionHandling ->
                            exceptionHandling
                                    .accessDeniedHandler(accessDeniedHandler())
                    )
                    .build();
    }
    
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}

