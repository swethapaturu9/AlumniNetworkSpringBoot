package com.project.alumninetwork.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.project.alumninetwork.pojo.Student;
import com.project.alumninetwork.pojo.StudentUser;
import com.project.alumninetwork.pojo.User;

import jakarta.servlet.http.HttpSession;

import com.project.alumninetwork.dao.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
	


    @Mock
    private Model model;
    @Mock
    private StudentDao studentDao;
    @Mock
    private StudentUserDao studentUserDao;
    @Mock
    private UserDao userDao;
    @Mock
    private HttpSession session;
    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private StudentController studentController;
    
    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testShowStudentSignUp() {
        String viewName = studentController.showStudentSignUp(model);
        verify(model, times(1)).addAttribute(eq("studentUser"), any(StudentUser.class));
        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
        assertEquals("student_signup", viewName);
    }
    
   
    
    @Test
    public void testShowStudentUpdate_InvalidUserSession() {
        when(session.getAttribute("user")).thenReturn(new User());
        when(studentUserDao.getStudentbyUser(any(User.class))).thenReturn(null);

        String viewName = studentController.showStudentUpdate(model, session, studentUserDao);

       
        assertEquals("student_update", viewName);
    }
    
    
    
    
    
    
    
    
}
