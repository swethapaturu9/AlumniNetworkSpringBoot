package com.project.alumninetwork.controller;







import java.util.List;
import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.alumninetwork.dao.StudentDao;
import com.project.alumninetwork.dao.StudentUserDao;
import com.project.alumninetwork.dao.UserDao;
import com.project.alumninetwork.pojo.Student;
import com.project.alumninetwork.pojo.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.project.alumninetwork.pojo.StudentUser;

@Controller
public class StudentController 
{	

//	@Autowired
//	private StudentDao studentDao;

    
    @GetMapping("/registerStudent")
	public String showStudentSignUp(Model model) {
		StudentUser studentUser = new StudentUser();
		User user = new User();
		
		 model.addAttribute("studentUser",studentUser);
		 model.addAttribute("user", user);
		 
		return "student_signup";
	}
    
    
    
    @PostMapping("/registerStudent")
	public String registerStudent(@RequestParam("studentID") int studentID, @RequestParam("email") String email, @Valid @ModelAttribute("studentUser") StudentUser studentUser, @Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			StudentDao studentDao, StudentUserDao studentUserDao, UserDao userDao,  Model model) throws IllegalStateException, IOException 
    {
    	
    	
    	String errorMessage = null;
    	
    	if(bindingResult.hasErrors())
    	{
    		model.addAttribute(studentUser);
    		model.addAttribute(user);
    		
    		return "student_signup";
    	}
   
    	user.setRole("STUDENT");
    	
  	    Student student = studentDao.getStudentbyId(studentID);
    	
  	    if(student == null)
   	      {   		 
  		     return "invalid_student";
  		  		
  	     }
  	    
  	    
  	    
  	  if (studentUserDao.getStudentbyStudentId(studentID) != null) {
  	    return "already_registered";
  	}
  	  
	
  	if(student.getEmail().equals(email))
  	{
  		userDao.save(user);
  		studentUser.setUser(user);
  		studentUser.setStudent(student);
  		String resume = studentUser.getStudent().getfName() + "_resume";
  		studentUser.setResumeName(resume);
  	    studentUserDao.save(studentUser);
  	    
  	    File dest = new File("/Users/swethapaturu/Desktop/files_project/" + resume);
  	    
  	    MultipartFile resume_file = studentUser.getResumeFile();
  	    
  	    resume_file.transferTo(dest);
  	    		
  	    List<StudentUser> studentusers = studentUserDao.getStudentUsers();
  	    
  	    for(StudentUser stuser: studentusers)
  	    {
  	    	System.out.println(stuser.getStudent().getlName());
  	    }
  	    
  	    return "success_registration";
  	  }
  	
  	  if (!student.getEmail().equals(email)) {
  	    errorMessage = "Incorrect email address";
  	    model.addAttribute("errorMessage", "Incorrect email address");
  	    return "student_signup";
  	    
  	  }
    	
  	  return "student_signup";
     
    		
    }


    
    @GetMapping("/updateStudent")
	public String showStudentUpdate(Model model, HttpSession session, StudentUserDao studentUserDao) {
		
    	 User user = (User) session.getAttribute("user");
    	 
    	 System.out.print("User: " + user.getUsername());
    	
		 model.addAttribute("user", user);
		 
		 StudentUser studentUser = studentUserDao.getStudentbyUser(user); 
		 
		 model.addAttribute("studentUser", studentUser);
		 
		return "student_update";
	}
    
    @PostMapping("/updateStudent")
    public String UpdateStudent(@Valid @ModelAttribute("studentUser") StudentUser studentUser,BindingResult bindingResult,StudentUserDao studentUserDao,Model model,RedirectAttributes redirectAttributes, HttpSession session ) throws IllegalStateException, IOException
    {
    	
    	
    	
    	 if (bindingResult.hasErrors()) {
    	        return "student_update";
    	    }
    	
    	User user = (User) session.getAttribute("user");
    	 
    	String linkedIN = studentUser.getLinkedIn();
 
    	
    	StudentUser validStudent = studentUserDao.getStudentbyUser(user); 
    	
    	validStudent.setLinkedIn(linkedIN); 
    	validStudent.setSkills(studentUser.getSkills());
    	validStudent.setPositionsInterested(studentUser.getPositionsInterested());
    	
    	String resume = validStudent.getStudent().getfName() + "_resume";
    	File dest = new File("/Users/swethapaturu/Desktop/files_project/" + resume);

    	// Check if the existing file exists and delete it if it does
    	if (dest.exists()) {
    	    dest.delete();
    	}
    	
    	MultipartFile resume_file = studentUser.getResumeFile();
    	resume_file.transferTo(dest);
    	
    	
    	validStudent.setResumeName(resume);
    	
    	studentUserDao.update(validStudent);
    	
    	
    	model.addAttribute("studentUser", validStudent);
    	model.addAttribute("message", "Your profile has been updated successfully!");
    
    	System.out.println(user.getUserid());
     	System.out.println(linkedIN); 
    
     	return "student_home";
    
    }
    
    
    
}
//
//}
