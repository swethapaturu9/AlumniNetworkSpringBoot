package com.project.alumninetwork.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.project.alumninetwork.pojo.User;

import jakarta.validation.Valid;

import com.project.alumninetwork.dao.AlumniDao;
import com.project.alumninetwork.dao.AlumniUserDao;
import com.project.alumninetwork.dao.JobDao;
import com.project.alumninetwork.dao.StudentUserDao;
import com.project.alumninetwork.dao.UserDao;
import com.project.alumninetwork.pojo.Alumni;
import com.project.alumninetwork.pojo.AlumniUser;
import com.project.alumninetwork.pojo.Job;
import com.project.alumninetwork.pojo.StudentUser;

@Controller
public class AlumniController 
{
	
	@GetMapping("/registerAlumni")
	public String showAlumniSignUp(Model model) {
		AlumniUser alumniUser = new AlumniUser();
		User user = new User();
		
		 model.addAttribute("alumniUser",alumniUser);
		 model.addAttribute("user", user);
		 
		return "alumni_signup";
	}
	
	 @PostMapping("/registerAlumni")
		public String registerAlumni(@RequestParam("alumniID") int alumniID, @Valid @ModelAttribute("alumniUser") AlumniUser alumniUser, @Valid @ModelAttribute("user") User user, BindingResult bindingResult,
				AlumniDao alumniDao, AlumniUserDao alumniUserDao, UserDao userDao,  Model model) throws IllegalStateException, IOException 
	    {
	    	
	 
	    	
	    	if(bindingResult.hasErrors())
	    	{
	    		model.addAttribute(alumniUser);
	    		model.addAttribute(user);
	    		
	    		return "alumni_signup";
	    	}
	   
	    	user.setRole("ALUMNI");
	    	
	  	    Alumni alumni = alumniDao.getAlumnibyId(alumniID);
	  	    		
	  	    if(alumni == null)
	   	      {   		 
	  		     return "invalid_alumni";
	  		  		
	  	     }
	  	    
	  	    
	  	    
	  	  if (alumniUserDao.getAlumnibyAlumniId(alumniID) != null)
	  	  {
	  		  return "already_registered";
	  	  }
	  	  
		
	  
	  		userDao.save(user);
	  		alumniUser.setUser(user);
	  		alumniUser.setAlumni(alumni);
	  		
	  	    alumniUserDao.save(alumniUser);
	  	    
	  	    		
	  	    List<AlumniUser> alumniusers = alumniUserDao.getAlumniUsers();
	  	    
	  	    for(AlumniUser auser: alumniusers)
	  	    {
	  	    	System.out.println(auser.getAlumni().getfName());
	  	    }
	  	    
	  	    return "success_registration";
	  
	    		     
	    		
	    }
	 
	 
	 @GetMapping("/ViewApplications")
	 public String viewApplications(@RequestParam("Id") int alumniUserId, Model model, JobDao jobDao) {
	     
		 List<Job> jobs = jobDao.getJobsbyAlumniId(alumniUserId);
		
		 model.addAttribute("jobs",jobs);
		 
		 return "view_apps";
	 }
	 
	 @GetMapping("/viewStudent")
		public String showStudent(@RequestParam("Id") int studentUserId, Model model, StudentUserDao studentUserDao ) 
	     {
		 
		    StudentUser validStudent = studentUserDao.getStudentUserbyID(studentUserId);
		    
		    if(validStudent == null)
		    {
		    	return "invalid_student";
		    }
		    
		    
		    model.addAttribute("studentUser", validStudent);
		 
		 
		    return "view_student";
			
		 }
	 
    
	
}
