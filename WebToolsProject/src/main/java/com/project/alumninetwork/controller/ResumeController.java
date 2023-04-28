package com.project.alumninetwork.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ResumeController 
{
	@GetMapping("/downloadResume/{filename:.+}")
	public void downloadResume(@PathVariable String filename, HttpServletResponse response) throws IOException {
	    Path filePath = Paths.get("/Users/swethapaturu/Desktop/files_project/" + filename);
	    Resource resource = new UrlResource(filePath.toUri());
	  
	    System.out.println("File path: " + filePath);
	    
	    
	    if (resource.exists()) {
	        response.setContentType("application/pdf");
	        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
	        IOUtils.copy(resource.getInputStream(), response.getOutputStream());
	    } else {
	        throw new FileNotFoundException("Resume file not found: " + filename);
	    }
	}

}
