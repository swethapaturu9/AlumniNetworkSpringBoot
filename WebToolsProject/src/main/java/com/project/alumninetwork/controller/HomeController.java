package com.project.alumninetwork.controller;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.alumninetwork.dao.JobDao;
import com.project.alumninetwork.dao.StudentUserDao;
import com.project.alumninetwork.dao.UserDao;
import com.project.alumninetwork.pojo.Job;
import com.project.alumninetwork.pojo.StudentUser;
import com.project.alumninetwork.pojo.User;

import jakarta.servlet.http.HttpSession;


@Controller 
public class HomeController 
{
	@GetMapping("")
	public String viewHomepage()
	{
		return "index";
	}
	
	@GetMapping("/viewJobs")
	public String viewJobs(JobDao jobDao, Model model, HttpSession session, UserDao userDao, StudentUserDao studentUserDao)
	{
		User user = (User) session.getAttribute("user");
	
		List<Job> jobs = jobDao.getAlljobs();
		
		if(user != null)
		{
			if(user.getRole().equals("STUDENT"))
			{
				StudentUser studentUser = studentUserDao.getStudentbyUser(user); 
				model.addAttribute("studentUser", studentUser);
				model.addAttribute("studentUserDao",studentUserDao);
			
				Set<Job> job_std = studentUser.getAppliedJobs();
				
				for(Job job: job_std)
				{
					
					System.out.println(job.getJob_id());
				}
					
				}
		}
		
		model.addAttribute("jobs",jobs);
		return "all_jobs";
		
	}
	
	
}
 