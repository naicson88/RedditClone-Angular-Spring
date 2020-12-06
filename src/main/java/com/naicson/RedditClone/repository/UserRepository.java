package com.naicson.RedditClone.repository;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naicson.RedditClone.model.User;

@Repository
@Configuration
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional <User> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
