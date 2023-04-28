package com.project.alumninetwork.pojo;


import java.util.Set;
import java.util.HashSet;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;



@Entity
@Table(name="studentUsers")
public class StudentUser 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	
	@Column(name = "skills")
    @ElementCollection
    private Set<String> skills;    
	
	@Transient
	 private MultipartFile resumeFile;
    
    @Column(name = "resume")
    private String resumeName;
    
    
    @Column(name = "positions_interested")
    @ElementCollection
    private Set<String> positionsInterested;    
    
    
    @Column(name = "url", length = 100)
    private String LinkedIn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "job_student",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<Job> appliedJobs = new HashSet<>();
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentID")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

	public Set<Job> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(Set<Job> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
	}

	public Set<String> getPositionsInterested() {
		return positionsInterested;
	}

	public void setPositionsInterested(Set<String> positionsInterested) {
		this.positionsInterested = positionsInterested;
	}

	public MultipartFile getResumeFile() {
		return resumeFile;
	}

	public void setResumeFile(MultipartFile resumeFile) {
		this.resumeFile = resumeFile;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getLinkedIn() {
		return LinkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		LinkedIn = linkedIn;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
