//package com.example.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.example.demo.JPARepository.RegistrationJPARepository;
//import com.example.demo.entity.Registration;
//import com.example.demo.secutity.CustomeUserDetails;
//
//public class UserDetailsServiceImpl implements UserDetailsService{
//
//	@Autowired
//	private RegistrationJPARepository registrationJPARepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Registration foundEmail = registrationJPARepository.getUserByEmail(username);
//		
//		if (foundEmail == null) {
//			throw new UsernameNotFoundException("Could not fount user !!");
//		}
//		
//		CustomeUserDetails customeUserDetails = new CustomeUserDetails(foundEmail);
//		return customeUserDetails;
//	}
//
//}
