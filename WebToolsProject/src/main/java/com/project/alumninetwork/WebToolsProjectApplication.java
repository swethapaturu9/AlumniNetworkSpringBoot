package com.project.alumninetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.project.alumninetwork.controller", "com.project.alumninetwork.dao", "com.project.alumninetwork.pojo"})
public class WebToolsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebToolsProjectApplication.class, args);
	}

}
