package com.personal.hms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.personal.hms.model.Patient;

public interface PatientRepository  extends MongoRepository<Patient, String> {
	
}
