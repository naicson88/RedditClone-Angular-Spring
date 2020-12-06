package com.naicson.RedditClone.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naicson.RedditClone.dto.AuthenticationResponse;
import com.naicson.RedditClone.dto.LoginRequest;
import com.naicson.RedditClone.dto.RegisterRequest;
import com.naicson.RedditClone.exceptions.SpringRedditException;
import com.naicson.RedditClone.model.NotificationEmail;	
import com.naicson.RedditClone.model.User;
import com.naicson.RedditClone.model.VerificationToken;
import com.naicson.RedditClone.repository.UserRepository;
import com.naicson.RedditClone.repository.VerificationTokenRepository;
//import com.naicson.RedditClone.security.JwtProvider;
import com.naicson.RedditClone.security.JwtUtils;

//import io.jsonwebtoken.JwtParser;
import jdk.jshell.spi.ExecutionControl.UserException;

@Service
@Transactional
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AuthenticationManager authManeger;
	@Autowired
	private JwtUtils jwtProvider;
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepository.save(user);
		
		String token = generationVerificationToken(user);
		emailService.sendEmail(new NotificationEmail("Please Active Your Account",
				user.getEmail(), "Thank you for singing up to Spring Reddit, " +
						"Please click on the link below to active your account: " +
						"http://localhost:8080/api/auth/accountVerification/" + token));
		
	}

	
	private String generationVerificationToken(User user) {
		
		String verificationToken = UUID.randomUUID().toString();
		VerificationToken vt = new VerificationToken();
		vt.setToken(verificationToken);
		vt.setUser(user);
		
		verificationTokenRepository.save(vt);
		
		return verificationToken;
	}
	
	public void verifyAccount(String token) {
	Optional <VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
	verificationToken.orElseThrow(() -> new SpringRedditException("Token Inválido!"));
	fecthUserAsEnable(verificationToken.get());
	
	}
	
	
	private void fecthUserAsEnable(VerificationToken verificationToken) {
		@NotBlank(message = "Nome do usuário é obrigatório!")
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("Usuario não encontrado com este nome!")) ;
		user.setEnabled(true);
		userRepository.save(user);
		
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication auth =  authManeger.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = jwtProvider.generateJwtToken(auth);
		return new AuthenticationResponse(token, loginRequest.getUsername());
		
	}
	
}
