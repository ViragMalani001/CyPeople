package com.example.demo.secutity;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

// Authentication Provider
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

	}

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//    	JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//    	
//    	theUserDetailsManager
//    		.setUsersByUsernameQuery("SELECT email, password, enabled FROM Registration WHERE email=?");
//    	
//    	theUserDetailsManager
//    		.setAuthoritiesByUsernameQuery("SELECT email, role FROM Registration WHERE email=?");
//	
//        return theUserDetailsManager;
//    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(configurer -> configurer
						.requestMatchers("/employees/emp-list").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/employees/emp-leave").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/employees/clients-list").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/employees/emp-department").hasAnyRole("MANAGER", "ADMIN")
		
						.requestMatchers("/emp-department-add").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/add-clients").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/projects-add").hasAnyRole("MANAGER", "ADMIN")
						.anyRequest().authenticated())

				.formLogin(form -> form.
						loginPage("/login")
						.loginProcessingUrl("/authentication")
						.defaultSuccessUrl("/dashboard", true)
						.failureUrl("/registration")
						.permitAll()

				).logout(logout -> logout.permitAll())

				// .exceptionHandling(configurer ->
				// configurer.accessDeniedPage("/access-denied")
				// );

				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()));
		return http.build();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
}
