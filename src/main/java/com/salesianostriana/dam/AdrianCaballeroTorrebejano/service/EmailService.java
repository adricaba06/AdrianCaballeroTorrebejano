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

    private final Locale locale = Locale.getDefault();

    public Email generateEmail(Student s) {
        final Context ctx = new Context(locale);

        ctx.setVariable("student", s);
        ctx.setVariable("course", s.getCourse());

        final String htmlContent = templateEngine.process("emailTemplate", ctx);

        // Imprime el HTML generado para que puedas revisarlo en consola
        System.out.println("HTML generado para el email:");
        System.out.println(htmlContent);

        Email email = new Email();
        email.setDestination(s.getEmail());
        email.setSubject("Your grades are here!");
        email.setMessage(htmlContent);

        return email;
    }

    public void sendEmail(Email email) {
        try {
            MimeMessage mimeMessage = jms.createMimeMessage();
            // El 'true' indica que se habilita multipart para adjuntos (aunque no tengas)
            // UTF-8 para evitar problemas con caracteres especiales
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email.getDestination());
            helper.setSubject(email.getSubject());
            // El segundo parámetro true indica que el texto es HTML
            helper.setText(email.getMessage(), true);
            helper.setFrom("adricaballero06@gmail.com");

            jms.send(mimeMessage);

            System.out.println("Email enviado correctamente a: " + email.getDestination());

        } catch (Exception e) {
            System.err.println("Error al enviar el email:");
            e.printStackTrace();
        }
    }

}

