package com.naicson.RedditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailContentBuilder {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	String build(String message) {
		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process("EmailTemplate", context);
	}
}
