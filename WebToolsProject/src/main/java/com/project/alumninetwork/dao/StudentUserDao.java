package com.project.alumninetwork.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.alumninetwork.pojo.Alumni;
import com.project.alumninetwork.pojo.Job;
import com.project.alumninetwork.pojo.Student;
import com.project.alumninetwork.pojo.StudentUser;
import com.project.alumninetwork.pojo.User;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


@Component
public class StudentUserDao extends Dao
{
	
	
	public List<StudentUser> getStudentUsers() {
		
		Query query = getSession().createQuery("FROM StudentUser");
		List<StudentUser> list = query.list();
		return list;
	}
	
	public Student getStudentbyStudentId(int studentid)
	{
		TypedQuery<StudentUser> query = getSession().createQuery(
                "SELECT su FROM StudentUser su WHERE su.student.studentID = :studentId", StudentUser.class);
        query.setParameter("studentId", studentid);
        try {
            StudentUser studentUser = query.getSingleResult();
            return studentUser.getStudent();
        } catch (NoResultException ex) {
            return null;
        }
	}
	
	public StudentUser getStudentbyUser(User user)
	{
		TypedQuery<StudentUser> query = getSession().createQuery(
                "SELECT su FROM StudentUser su WHERE su.user. userid = :userId", StudentUser.class);
        query.setParameter("userId", user.getUserid());
        try {
            StudentUser studentUser = query.getSingleResult();
            return studentUser;
        } catch (NoResultException ex) {
            return null;
        }
		
	}
	public StudentUser getStudentUserbyID(int Id)
	{
		 StudentUser studentUser = session.get(StudentUser.class, Id);
		 
		 return studentUser;
		
	}
	public boolean hasAppliedforJob(StudentUser studentUser, Job job)
	{
		 
		System.out.println(studentUser.getId());
		System.out.println(job.getJob_id());
		
		Set<Job> job_std = studentUser.getAppliedJobs();
		
		for(Job job_s: job_std)
		{
			System.out.println(job_s.getJob_id());
			
			if(job_s.getJob_id()==job.getJob_id())
			{
				return true;
			}
		}
			
	
		return false;
		
	}
	
	public void save(StudentUser studentUser) {
		begin();
		getSession().save(studentUser);
		commit();
	}
	
	
	public void update(StudentUser studentUser) {
		
		try{
			begin();
	        getSession().update(studentUser);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

	public void merge(StudentUser studentUser) {
		try{
			begin();
	        getSession().merge(studentUser);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

	public void delete(StudentUser studentUser) {
		try{
			begin();
	        getSession().delete(studentUser);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

}
