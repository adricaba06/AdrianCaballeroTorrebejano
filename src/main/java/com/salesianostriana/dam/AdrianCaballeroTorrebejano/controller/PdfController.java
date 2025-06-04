package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.PdfService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PdfController {
	

    @Autowired
    private PdfService pdfService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;

    @GetMapping("/downloadPdf")
    public void descargarPdf(HttpServletResponse response, @RequestParam Long  courseId) {
        try {
            
        	Optional<Course> courseO = courseService.findById(courseId);
        	Course course = null;
        	
        	if(courseO.isPresent()) {
        		course = courseO.get();
        	}
        	
        	List<Student> students = studentService.filterStudentsByCourseId(courseId);
            String html = pdfService.parseThymeleafTemplate(students, course);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(baos);
            renderer.finishPDF();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=students.pdf");
            response.setContentLength(baos.size());

            baos.writeTo(response.getOutputStream());
            response.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

