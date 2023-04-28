package com.project.alumninetwork.controller;

import com.project.alumninetwork.dao.AlumniUserDao;
import com.project.alumninetwork.pojo.AlumniUser;
import com.project.alumninetwork.pojo.User;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController 
{
	@PostMapping("/email")
    public String sendEmail(@RequestParam("toAddress") String toAddress,
                            @RequestParam("subject") String subject,
                            @RequestParam("message") String message,
                            Model model, HttpSession session, AlumniUserDao alumniUserDao ) 
	{
		
		User user = (User) session.getAttribute("user");
		
		AlumniUser validAlumni = alumniUserDao.getAlumnibyUser(user);
		
		if(validAlumni == null)
		{
			return "invalid_alumni";
		}
		
		System.out.println(toAddress);
		System.out.println(subject);
		System.out.println(message);
		

        try {
            Email email = new SimpleEmail();
            email.setHostName("sandbox.smtp.mailtrap.io");
            email.setSmtpPort(2525);
            email.setAuthenticator(new DefaultAuthenticator("6e173dfb1a07e4", "cb97f71a15d68f"));
            email.setStartTLSEnabled(true);
            email.setFrom(validAlumni.getEmail());
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(toAddress);
            email.send();

            
        } catch (EmailException e) {
           System.out.println(e);
        }

        return "success_registration";
    }

}
