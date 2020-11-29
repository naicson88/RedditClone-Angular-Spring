package com.naicson.RedditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthenticationResponse {
	private String authenticationToken;
	private String username;
	
	public AuthenticationResponse() {
		
	}
	
	public AuthenticationResponse(String authenticationToken, String username) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
	}
	
	
}
