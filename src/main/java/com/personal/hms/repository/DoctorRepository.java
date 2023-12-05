package com.personal.hms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.personal.hms.model.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String>  {

    
    @Query(value="{name:'?0'}", fields="{'specialization' : 1, 'experience' : 1}")
    List<Doctor> findAll(String category);
    
    @Query("{$or :[{name: '?0', specialization:'?1'}")
    List<Doctor> find(String name, String specialization);
    
    public long count();

}
