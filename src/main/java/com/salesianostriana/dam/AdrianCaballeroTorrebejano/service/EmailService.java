package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Email;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

import ch.qos.logback.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jms;

	@Autowired
	private TemplateEngine templateEngine;
	
	private final Locale locale = Locale.getDefault();

	public Email generateEmail(Student s) {
		final Context ctx = new Context(locale);

		// Generar el contenido HTML a partir de una sola plantilla
		final String htmlContent = templateEngine.process("emailTemplate", ctx);

		Email email = new Email();
		email.setDestination(s.getEmail());
		email.setSubject("Your grades are here!");
		email.setMessage(htmlContent, true);

		return email;
	}

	public void sendEmail(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getDestination());
		message.setSubject(email.getSubject());
		message.setText(email.getMessage());
		message.setFrom("adricaballero06@gmail.com");
		jms.send(message);
	}

}
