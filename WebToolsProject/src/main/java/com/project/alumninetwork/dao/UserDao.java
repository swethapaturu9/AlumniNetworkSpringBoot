package com.project.alumninetwork.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.project.alumninetwork.pojo.StudentUser;
import com.project.alumninetwork.pojo.User;

import jakarta.persistence.NoResultException;



@Component
public class UserDao extends Dao 
{
	public UserDao() {

	}

	public List<User> getUsers() {
		Query query = getSession().createQuery("FROM User");
		List<User> list = query.list();
		return list;
	}
	
//	try {
//        StudentUser studentUser = query.getSingleResult();
//        return studentUser.getStudent();
//    } catch (NoResultException ex) {
//        return null;
//    }
	
	
	public User getUserbyUsername(String username)
	{
		 Query query = getSession().createQuery("FROM User WHERE username = :username");
		 query.setParameter("username", username);
		 try {
		 User user = (User) query.uniqueResult();
		 return user;
		 } catch(NoResultException ex) {
			 return null;
		 }
		
	}

	public void save(User user) {
		try{
			begin();
	        getSession().save(user);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

	public void update(User user) {
		try{
			begin();
	        getSession().update(user);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

	public void merge(User user) {
		try{
			begin();
	        getSession().merge(user);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

	public void delete(User user) {
		try{
			begin();
	        getSession().delete(user);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		}
	}
}
