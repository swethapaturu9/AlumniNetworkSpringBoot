package com.project.alumninetwork.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import static org.junit.jupiter.api.Assertions.assertThrows;


import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ResumeControllerTest {

    @InjectMocks
    private ResumeController resumeController;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Resource resource;

    @Test
    public void testDownloadResume_Success() throws IOException {
    	String filename = "Swetha_resume";
    	String expectedFilePath = "/Users/swethapaturu/Desktop/files_project/" + filename;

    	// Mock the behavior of Resource
    	when(resource.exists()).thenReturn(true);
    	when(resource.getInputStream()).thenReturn(new ByteArrayInputStream("PDF content".getBytes()));

    	// Use the actual Paths.get(...) method to create a Path object
    	Path path = Paths.get(expectedFilePath);

    	// Create a mock HttpServletResponse
    	MockHttpServletResponse mockResponse = new MockHttpServletResponse();

    	resumeController.downloadResume(filename, mockResponse);

    	// Verify that the mockResponse was set correctly
    	assertEquals("application/pdf", mockResponse.getContentType());
    	assertEquals("attachment; filename=" + "\"" + filename + "\"" , mockResponse.getHeader(HttpHeaders.CONTENT_DISPOSITION));
    }

    @Test
    public void testDownloadResume_FileNotFound() throws IOException {
    	
        String filename = "nonexistent.pdf";

        
        when(resource.exists()).thenReturn(false);

        
        assertThrows(FileNotFoundException.class, () -> resumeController.downloadResume(filename, response));
    }

    
}