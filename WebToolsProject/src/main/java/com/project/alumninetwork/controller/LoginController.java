package com.project.alumninetwork.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.alumninetwork.dao.AlumniUserDao;
import com.project.alumninetwork.dao.StudentUserDao;
import com.project.alumninetwork.dao.UserDao;
import com.project.alumninetwork.pojo.AlumniUser;
import com.project.alumninetwork.pojo.StudentUser;
import com.project.alumninetwork.pojo.User;

import jakarta.validation.Valid;

@Controller
public class LoginController 
{
	@GetMapping("/login")
	public String Login(Model model)
	{
		User user = new User();
		
		model.addAttribute("user",user);
		
		return "user_login";
	}
	
	
	 @PostMapping("/login")
		public String Login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
				StudentUserDao studentUserDao, AlumniUserDao alumniUserDao, UserDao userDao,  Model model, HttpSession session) 
	    {
		 
		 
		 String errorMessage = null;
		 
		 String username = user.getUsername();
		 String password = user.getPassword();
		 
		 
		 System.out.println("Username: " + username);
		 System.out.println("Password: " + password);
		 
		
		 User validUser = userDao.getUserbyUsername(username);
		 
		 if(validUser == null)
		 {
			 System.out.println("NO valid user");
			 
		 }
		 
		 else
		 {
		 System.out.println("Username: " + validUser.getUsername());
		 System.out.println("Password: " + validUser.getPassword());
		 System.out.println("Role: " + validUser.getRole());
		 
		 }
		 
		 if(validUser!=null && validUser.getPassword().equals(password))
		 {
			 
			 System.out.println("IN Valid user if ");
			 
       		 String role = validUser.getRole();
       		 
       		  
       		 session.setAttribute("user", validUser);
       		 
       		 
       		 if(role.equals("STUDENT"))
       		 {
			      StudentUser studentUser = studentUserDao.getStudentbyUser(validUser);
			      
			      if(studentUser == null)
			      {
			    	  return "invalid_login";
			      }
			      
			    model.addAttribute("studentUser", studentUser);
			    
			    System.out.println("Name" + studentUser.getStudent().getfName());
			    return "student_home";
       		 
       		 }
       		 else
       		 {
       			AlumniUser alumniUser = alumniUserDao.getAlumnibyUser(validUser);
			      
			      if(alumniUser == null)
			      {
			    	  return "invalid_login";
			      }
			      
			    model.addAttribute("alumniUser", alumniUser);
			    
			    System.out.println("Name" + alumniUser.getAlumni().getfName());
       			 
       			 return "alumni_home";
       		 }
			 
		 } 
		 
		 
		 errorMessage = "Incorrect username or password, Please sign up if new user";
	  	 model.addAttribute("errorMessage", errorMessage);
		 
		 return "user_login";
		 
	    }
	 
	 
	 @GetMapping("/home")
	 String getView(HttpSession session, Model model,StudentUserDao studentUserDao, AlumniUserDao alumniUserDao)
	 {
		 User user = (User) session.getAttribute("user");
		 
		 if(user == null)
		 {
			 return "invalid_user";
		 }
		 
		 
		 if(user.getRole().equals("STUDENT"))
		 {
			 if(studentUserDao.getStudentbyUser(user) == null)
				   return "invalid_student";
			 
			 model.addAttribute("studentUser", studentUserDao.getStudentbyUser(user));
			 return "student_home";
		 }
		 
		 if(alumniUserDao.getAlumnibyUser(user) == null)
			   return "invalid_alumni";
		 
		 model.addAttribute("alumniUser", alumniUserDao.getAlumnibyUser(user));
		 return "alumni_home";
	
		 
	 }
	 
	 
	 @PostMapping("/logout")
	 public String logOut(HttpSession session) 
	    {
		 
		  session.invalidate();
		  return "index";	
	    }
	 
	 
}
