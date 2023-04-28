package com.project.alumninetwork.pojo;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;

@Entity
public class Job 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id", unique = true, nullable = false)
	private int job_id;

	
	@Column(name="Company")
	private String company; 

	
	@Column(name="JD")
	private String jobDescription; 
	
	@Column(name="skills_required")
    @ElementCollection
    private Set<String> skills_required;    
	
	
	@OneToOne
	private AlumniUser postedBy; 
	
	@ManyToMany(mappedBy = "appliedJobs",fetch = FetchType.EAGER)
	 private Set<StudentUser> appliedStudents = new HashSet<>();
	
	
	public AlumniUser getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(AlumniUser postedBy) {
		this.postedBy = postedBy;
	}



	public Set<StudentUser> getAppliedStudents() {
		return appliedStudents;
	}

	public void setAppliedStudents(Set<StudentUser> appliedStudents) {
		this.appliedStudents = appliedStudents;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public Set<String> getSkills_required() {
		return skills_required;
	}

	public void setSkills_required(Set<String> skills_required) {
		this.skills_required = skills_required;
	}



}
