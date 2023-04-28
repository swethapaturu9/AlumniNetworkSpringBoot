package com.project.alumninetwork.dao;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.project.alumninetwork.util.HibernateUtil;


public class Dao
{
	
	
	SessionFactory sf;
	Session session;
	Transaction tx;

	public Dao() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		tx = null;
	}

	public void begin() {
		
		tx = session.beginTransaction();
	}

	public Session getSession() {
		return this.session;
	}

	protected void commit() {
		tx.commit();
	}

	public void close() {
		session.close();
	}
	 
	  
}