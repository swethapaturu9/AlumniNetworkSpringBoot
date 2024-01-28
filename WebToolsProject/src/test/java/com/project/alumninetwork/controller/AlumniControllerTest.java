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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;

import com.project.alumninetwork.pojo.Alumni;
import com.project.alumninetwork.pojo.AlumniUser;
import com.project.alumninetwork.pojo.User;
import com.project.alumninetwork.dao.*;



@ExtendWith(SpringExtension.class)
@WebMvcTest(AlumniController.class)
public class AlumniControllerTest {
	
	
	    @Autowired
	    private MockMvc mockMvc;
	 
	    @Mock
	    private Model model;
	    
	    @Mock
	    private AlumniDao alumniDao;
	    
	    @Mock
	    private AlumniUserDao alumniUserDao;
	    
	    @Mock
	    private UserDao userDao;
	    
	    @Mock
	    private JobDao jobDao;
	    
	    @Mock
	    private StudentUserDao studentUserDao;


	    @InjectMocks
	    private AlumniController alumniController;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	
	@Test
    public void testShowAlumniSignUp() throws Exception {
        mockMvc.perform(get("/registerAlumni"))
               .andExpect(status().isOk())
               .andExpect(view().name("alumni_signup"))
               .andExpect(model().attributeExists("alumniUser"))
               .andExpect(model().attributeExists("user"));
        
       String viewName = alumniController.showAlumniSignUp(model);
        
        verify(model, times(1)).addAttribute(eq("alumniUser"), any(AlumniUser.class));
        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
        assertEquals("alumni_signup", viewName);
    }
	
	
	@Test
	public void testRegisterAlumni_Success() throws IllegalStateException, IOException {
	    
	    AlumniUser alumniUser = new AlumniUser();
	    User user = new User();
	    int alumniID = 1;
	    BindingResult bindingResult = mock(BindingResult.class);
	    when(alumniDao.getAlumnibyId(alumniID)).thenReturn(new Alumni());
	    when(alumniUserDao.getAlumnibyAlumniId(alumniID)).thenReturn(null);

	    String viewName = alumniController.registerAlumni(alumniID, alumniUser, user, bindingResult, alumniDao, alumniUserDao, userDao, model);

	    assertEquals("success_registration", viewName);
	    verify(userDao, times(1)).save(any(User.class));
	    verify(alumniUserDao, times(1)).save(any(AlumniUser.class));
	}
	
	

  

 
}
