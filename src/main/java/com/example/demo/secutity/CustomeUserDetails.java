package com.example.demo.secutity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Registration;

public class CustomeUserDetails implements UserDetails {
	
	private Registration registration;
	
	public CustomeUserDetails(Registration registration) {
		super();
		this.registration = registration;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(registration.getRole()); 
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return registration.getPassword();
	}

	@Override
	public String getUsername() {
		return registration.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
