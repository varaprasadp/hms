package com.personal.hms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("doctor")
public class Doctor {
	@Id
    private String id;
	private String firstName;
	private String lastName;
	private String DOB;
	private String gender;
	private Integer experience;
	private String specialization;
	private String registeredNo;
	private String deptID;
	private String contact;
	private String address;
	private String degrees;
	private Consultation[] consultations;
}
