package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.OutputStream;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;




@Service
public class PdfService { //https://www.baeldung.com/thymeleaf-generate-pdf

	public String parseThymeleafTemplate(List<Student> students, Course course) {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setSuffix(".html");
	    templateResolver.setPrefix("templates/");

	    templateResolver.setTemplateMode(TemplateMode.HTML);

	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);

	    Context context = new Context();
	    context.setVariable("students", students);
	    context.setVariable("course", course);

	    return templateEngine.process("pdflistOfStudents", context);
	}
	
	public void generatePdfFromHtml(String html, List<Student> students, Course course) throws IOException {
		String outputFolder = System.getProperty("user.home") + java.io.File.separator + "students.pdf";

        try (OutputStream outputStream = new FileOutputStream(outputFolder)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }

    public void generatePdfFromStudents(List<Student> students, Course course) throws IOException {
        String html = parseThymeleafTemplate(students, course);
        generatePdfFromHtml(html, students, course);
    }
}
