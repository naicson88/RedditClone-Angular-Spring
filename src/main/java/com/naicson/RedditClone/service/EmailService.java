package com.naicson.RedditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.naicson.RedditClone.exceptions.SpringRedditException;
import com.naicson.RedditClone.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private EmailContentBuilder mailContentBuilder;
	
	public void sendEmail (NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("springreddit@email.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
		};
		
		try {
			mailSender.send(messagePreparator);
			log.info("Email de ativação enviado!!");
		}catch (MailException e){
			throw new SpringRedditException ("Ocorreu um erro ao tentar enviar o email para " + notificationEmail.getRecipient() );
		}
		
	}
}
