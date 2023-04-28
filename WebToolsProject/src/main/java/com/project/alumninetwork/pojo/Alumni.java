package com.project.alumninetwork.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Alumni")
public class Alumni 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AlumniID", unique = true, nullable = false)
	private int alumniID;
	
	@Column(name="firstName")
	private String fName; 
	
	@Column(name="lastName")
	private String lName;

	
	@Column(name="Year_Graduated")
	private float graduationYear;


	public int getAlumniID() {
		return alumniID;
	}

	public void setAlumniID(int alumniID) {
		this.alumniID = alumniID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public float getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(float graduationYear) {
		this.graduationYear = graduationYear;
	}
	

}
