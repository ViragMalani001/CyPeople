package com.example.demo.secutity;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails virag = User.builder()
				.username("ViragMalani")
				.password("{noop}viragmalani")
				.roles("EMPLOYEE").build();
		UserDetails priyesh = User.builder()
				.username("PriyeshGajera")
				.password("{noop}1234")
				.roles("MANAGER")
				.build();
		UserDetails shekhar = User.builder()
				.username("ShekharPatel")
				.password("{noop}shekharpatel")
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(virag, priyesh, shekhar);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer.requestMatchers("/employees/emp-list")
				.hasAnyRole("MANAGER", "ADMIN").requestMatchers("/employees/emp-leave").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/employees/clients-list").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/employees/emp-department").hasAnyRole("MANAGER", "ADMIN")

				.requestMatchers("/emp-department-add").hasAnyRole("MANAGER", "ADMIN").requestMatchers("/add-clients")
				.hasAnyRole("MANAGER", "ADMIN").requestMatchers("/projects-add").hasAnyRole("MANAGER", "ADMIN")
				.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/authentication")
						.defaultSuccessUrl("/dashboard", true)
//						.failureUrl("/registration")
						.permitAll()

				).logout(logout -> logout.permitAll())

				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()));
		return http.build();
	}

	@Bean
	public static AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//		JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//		theUserDetailsManager
//				.setUsersByUsernameQuery("select email, password, enabled from Registration where email=?");
//
//		theUserDetailsManager.setAuthoritiesByUsernameQuery("select email, role from Registration where email=?");
//
//		return theUserDetailsManager;
//	}
	
//	@Bean
//	public UserDetailsService getUserDetailService() {
//		return new UserDetailsServiceImpl();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	} 
//
//// Authentication Provider
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
//	
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

//   @Configuration
//    protected static class WebSecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//    		http
//			.authorizeHttpRequests(configurer -> configurer
//					.requestMatchers("/employees/emp-list").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/employees/emp-leave").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/employees/clients-list").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/employees/emp-department").hasAnyRole("MANAGER", "ADMIN")
//	
//					.requestMatchers("/emp-department-add").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/add-clients").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/projects-add").hasAnyRole("MANAGER", "ADMIN")
//					.anyRequest().authenticated()
//			)
//
//			.formLogin(form -> form
//					.loginPage("/login")
//					.loginProcessingUrl("/authentication")
//					.defaultSuccessUrl("/dashboard", true)
//					.failureUrl("/login?error=true")
//					.permitAll()
//
//			)
//			.logout(logout -> logout.permitAll())
//			
//			.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()));
//        }
//    }
}
