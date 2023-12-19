package com.personal.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class HmsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HmsApplication.class, args);
		
	}

}

