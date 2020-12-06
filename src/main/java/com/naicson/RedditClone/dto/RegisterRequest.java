package com.naicson.RedditClone.dto;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.naicson.RedditClone.model.Role;
import com.naicson.RedditClone.model.User;


public class RegisterRequest  implements UserDetails{
	

	private static final long serialVersionUID = 1L;
	private String email;
	private String username;
	private String password;
	
	public RegisterRequest() {
		
	}
	
	private Collection<? extends GrantedAuthority> authorities;

	public RegisterRequest(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
	
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static RegisterRequest build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
				

		return new RegisterRequest(
				user.getUserID(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
