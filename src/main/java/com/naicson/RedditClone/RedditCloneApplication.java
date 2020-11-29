package com.naicson.RedditClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.naicson.RedditClone.controller.AuthController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.naicson.RedditClone.repository")
@ComponentScan("com.naicson.RedditClone.controller")
@EntityScan("com.naicson.RedditClone.model")
@ComponentScan("com.naicson.RedditClone.service")
@ComponentScan("com.naicson.RedditClone.security")
public class RedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}

}
