package com.naicson.RedditClone.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.naicson.RedditClone.dto.RegisterRequest;

public interface RegisterRequestService {
	 RegisterRequest loadUserByUsername(String username) throws UsernameNotFoundException;
}
