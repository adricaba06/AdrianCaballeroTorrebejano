package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Email;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

import jakarta.mail.internet.MimeMessage;

import org.thymeleaf.context.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender jms;

    @Autowired
    private TemplateEngine templateEngine;

    private final Locale locale = Locale.getDefault(); //idioma por defecto aparentemente

    public Email generateEmail(Student s) {
        final Context ctx = new Context(locale); //esto es el motor de plantilla, es decir el que pasa las variables a la plantilla como un model practicamente

        ctx.setVariable("student", s);
        ctx.setVariable("course", s.getCourse());

        final String htmlContent = templateEngine.process("emailTemplate", ctx); // esta es la plantilla

        Email email = new Email();
        email.setDestination(s.getEmail());
        email.setSubject("Your grades are here!");
        email.setMessage(htmlContent);

        return email;
    }

    public void sendEmail(Email email) {
        try {
            MimeMessage mimeMessage = jms.createMimeMessage(); //esto es lo que me permite adjuntar archivos como el html, representa un correo completo
     
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); //helper  me ayuda a configurarlo facilmente

            helper.setTo(email.getDestination());
            helper.setSubject(email.getSubject());
  
            helper.setText(email.getMessage(), true);
            helper.setFrom("adricaballero06@gmail.com");

            jms.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

