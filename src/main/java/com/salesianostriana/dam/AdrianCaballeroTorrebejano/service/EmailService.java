package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jms;
	
	public void sendEmail(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getDestination());
		message.setSubject(email.getSubject());
		message.setText(email.getMessage());
		message.setFrom("adricaballero06@gmail.com");
		jms.send(message);
	}
	

}
