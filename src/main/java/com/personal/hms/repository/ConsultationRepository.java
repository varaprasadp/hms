package com.personal.hms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.personal.hms.model.Consultation;

public interface ConsultationRepository extends MongoRepository<Consultation, String>{
//    @Query("{$or :[{doctorID: '?0'}")
//    List<Consultation> find(String doctorID);
}
