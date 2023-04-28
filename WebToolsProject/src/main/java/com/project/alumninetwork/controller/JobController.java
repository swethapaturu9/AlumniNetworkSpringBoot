package com.project.alumninetwork.controller;

import com.project.alumninetwork.dao.AlumniUserDao;
import com.project.alumninetwork.dao.JobDao;
import com.project.alumninetwork.dao.StudentUserDao;
import com.project.alumninetwork.dao.UserDao;
import com.project.alumninetwork.pojo.AlumniUser;
import com.project.alumninetwork.pojo.Job;
import com.project.alumninetwork.pojo.StudentUser;
import com.project.alumninetwork.pojo.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController 
{
	@GetMapping("/PostJob")
	public String ViewJobForm(Model model)
	{
		
		Job job = new Job();
		
		model.addAttribute("job",job);
		
		
		return "add_job";

	}
	
	
	@PostMapping("/PostJob")
	public String HandleJobForm(@Valid @ModelAttribute("job") Job job, BindingResult bindingResult,
			AlumniUserDao alumniUserDao, AlumniUser alumniUser, UserDao userDao, JobDao jobDao, Model model, HttpSession session)
	{
		
		if(bindingResult.hasErrors())
    	{
    		model.addAttribute(job);
    		return "add_job";
    	}
		
		
		User user = (User) session.getAttribute("user");
		
		if(user == null)
		{
			return "invalid_alumni";
		}
		
		
		
		AlumniUser validAlumni = alumniUserDao.getAlumnibyUser(user); 
	
		if(validAlumni == null)
		{
			return "invalid_alumni";
		}
		
		
		
			System.out.println("Job poster Name: " + validAlumni.getAlumni().getfName());
			 
		    job.setPostedBy(validAlumni);
		    
		    jobDao.save(job);
		
		    List<Job> jobs = jobDao.getJobsbyAlumniId(validAlumni.getId());
		    
		    model.addAttribute("jobs",jobs);
		
		    return "view_apps";

	}
	
	
	@PostMapping("/applyJob")
	public String applyJob(@RequestParam("jobId") int jobId, HttpSession session, StudentUserDao studentUserDao, JobDao jobDao, Model model ) 
	{
		
		User user = (User) session.getAttribute("user");
		
		if(user == null)
		{
			return "invalid_student";
		}
		
		StudentUser validStudent = studentUserDao.getStudentbyUser(user);
		
		if(validStudent == null)
		{
			return "invalid_student";
		}
		
		
		
		Job job = jobDao.getJobbyId(jobId);
		
		job.getAppliedStudents().add(validStudent);
		
		jobDao.merge(job);
		
		validStudent.getAppliedJobs().add(job);
		
		studentUserDao.merge(validStudent);
		model.addAttribute("studentUser", validStudent);
		model.addAttribute("studentUserDao",studentUserDao);
		
		List<Job> jobs = jobDao.getAlljobs();
		model.addAttribute("jobs", jobs);
		
	    return "all_jobs";
	}
	
	@PostMapping("/deleteJob")
	public String deleteJob(@RequestParam("jobId") int jobId, HttpSession session, AlumniUserDao alumniUserDao, JobDao jobDao, Model model, StudentUserDao studentUserDao ) 
	{
        User user = (User) session.getAttribute("user");
		
		if(user == null)
		{
			return "invalid_user";
		}
		
	
		AlumniUser validAlumni = alumniUserDao.getAlumnibyUser(user);
		
		if(validAlumni == null)
		{
			return "invalid_alumni";
		}
		
		Job job = jobDao.getJobbyId(jobId);
		validAlumni.getPosted_jobs().remove(job);
		alumniUserDao.merge(validAlumni);
		
		
		Set<StudentUser> appliedStudents = job.getAppliedStudents();
		
		for (StudentUser student : appliedStudents) {
		    student.getAppliedJobs().remove(job);
		    studentUserDao.merge(student);
		    
		}
		
		jobDao.delete(job);
		
		
		 List<Job> jobs = jobDao.getJobsbyAlumniId(validAlumni.getId());
			
		 model.addAttribute("jobs",jobs);
		 
		  return "view_apps";

	}
}
