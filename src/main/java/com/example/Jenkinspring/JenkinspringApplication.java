package com.example.Jenkinspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JenkinspringApplication {

@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JenkinspringApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JenkinspringApplication.class, args);
	}

}
