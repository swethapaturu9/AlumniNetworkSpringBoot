package com.project.alumninetwork.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.project.alumninetwork.pojo.Alumni;


@Component
public class AlumniDao extends Dao
{
	public AlumniDao() {

	}

	public List<Alumni> getAlumni() {
		
		Query query = getSession().createQuery("FROM Alumni");
		List<Alumni> list = query.list();
		return list;
	}

	public Alumni getAlumnibyId(int alumniId)
	{
		 Alumni alumni = session.get(Alumni.class, alumniId);
		 
		 return alumni;
		
	}

	

}
