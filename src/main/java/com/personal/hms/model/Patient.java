package com.personal.hms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("patient")
public class Patient {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String DOB;
	private String gender;
	private String contact;
	private String address;
	private String registerdDate;
	
}
