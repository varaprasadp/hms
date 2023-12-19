package com.personal.hms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("consultation")
public class Consultation {
	@Id
    private String id;
	private String patientID;
	private String doctorID;
	private String diagnosis;
	private String medicine;
}
