package com.project.alumninetwork.dao;



import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.project.alumninetwork.pojo.Student;
//import com.project.alumninetwork.util.HibernateUtil;


@Component	
public class StudentDao extends Dao 
{

	public StudentDao() {

	}

	public List<Student> getStudents() {
		
		Query query = getSession().createQuery("FROM Student");
		List<Student> list = query.list();
		return list;
	}

	public Student getStudentbyId(int studentid)
	{
		 Student student = session.get(Student.class, studentid);
		 
		 return student;
		
	}

	
}
