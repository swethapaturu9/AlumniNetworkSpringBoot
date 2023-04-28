package com.project.alumninetwork.pojo;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class AlumniUser 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@Column(name="Email")
	@Email
	@NotBlank
	private String email; 
	
	
    @Column(name="Company")
	private String company; 

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Alumni getAlumni() {
		return alumni;
	}


	public void setAlumni(Alumni alumni) {
		this.alumni = alumni;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@OneToMany(mappedBy="postedBy",cascade = CascadeType.ALL)
	Set<Job> posted_jobs;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumniID")
    private Alumni alumni;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public Set<Job> getPosted_jobs() {
		return posted_jobs;
	}


	public void setPosted_jobs(Set<Job> posted_jobs) {
		this.posted_jobs = posted_jobs;
	} 
	
	
	

}
