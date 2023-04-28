package com.project.alumninetwork.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.alumninetwork.pojo.Job;


@Component
public class JobDao extends Dao 
{
	
	public List<Job> getAlljobs() {
		Query query = getSession().createQuery("FROM Job");
		List<Job> list = query.list();
		return list;
	}
	
	
	public Job getJobbyId(int jobid)
	{
		
	    Job job = null;
	    try {
	        Query query = getSession().createQuery("SELECT DISTINCT j FROM Job j LEFT JOIN FETCH j.appliedStudents WHERE j.job_id=:jobId");
	        query.setParameter("jobId", jobid);
	        job = (Job) query.getSingleResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return job;
		
	}
	
	public List<Job> getJobsbyAlumniId(int alumniId)
	{
		String hql = "FROM Job j WHERE j.postedBy.id = :alumniId";
	    Query query = getSession().createQuery(hql);
	    query.setParameter("alumniId", alumniId);
	    return query.getResultList();
		
	}
	
	
	public void save(Job job) {
		try{
			begin();
	        getSession().save(job);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		}
	}

	public void update(Job job) {
		try{
			begin();
	        getSession().update(job);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		}
	}

	public void merge(Job job) {
		try{
			begin();
	        getSession().merge(job);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

	public void delete(Job job) {
		try{
			begin();
	        getSession().delete(job);
		    commit();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}

}
