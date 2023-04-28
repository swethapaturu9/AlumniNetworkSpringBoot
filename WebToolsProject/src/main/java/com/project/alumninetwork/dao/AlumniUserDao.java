package com.project.alumninetwork.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.project.alumninetwork.pojo.Alumni;
import com.project.alumninetwork.pojo.AlumniUser;
import com.project.alumninetwork.pojo.User;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Component
public class AlumniUserDao extends Dao 
{
	 public List<AlumniUser> getAlumniUsers() {
	        Query query = getSession().createQuery("FROM AlumniUser");
	        List<AlumniUser> list = query.list();
	        return list;
	    }
	    
	    public Alumni getAlumnibyAlumniId(int alumniId) {
	        TypedQuery<AlumniUser> query = getSession().createQuery(
	                "SELECT au FROM AlumniUser au WHERE au.alumni.alumniID = :alumniId", AlumniUser.class);
	        query.setParameter("alumniId", alumniId);
	        try {
	            AlumniUser alumniUser = query.getSingleResult();
	            return alumniUser.getAlumni();
	        } catch (NoResultException ex) {
	            return null;
	        }
	    }
	    
	    public AlumniUser getAlumnibyUser(User user) {
	        TypedQuery<AlumniUser> query = getSession().createQuery(
	                "SELECT au FROM AlumniUser au WHERE au.user.userid = :userId", AlumniUser.class);
	        query.setParameter("userId", user.getUserid());
	        try {
	            AlumniUser alumniUser = query.getSingleResult();
	            return alumniUser;
	        } catch (NoResultException ex) {
	            return null;
	        }
	    }
	    
	    public void save(AlumniUser alumniUser) {
	    	try{
				begin();
		        getSession().save(alumniUser);
			    commit();
			} catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();
		        }
		        throw e;
			} 
	    }
	    
	    
	    public void update(AlumniUser alumniUser) {
	    	try{
				begin();
		        getSession().update(alumniUser);
			    commit();
			} catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();
		        }
		        throw e;
			} 
	    }

	    public void merge(AlumniUser alumniUser) {
	    	try{
				begin();
		        getSession().merge(alumniUser);
			    commit();
			} catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();
		        }
		        throw e;
			} 
	    }

	    public void delete(AlumniUser alumniUser) {
	    	try{
				begin();
		        getSession().delete(alumniUser);
			    commit();
			} catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();
		        }
		        throw e;
			} 
	    }

}
