package com.personal.hms.dataFetchers;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.personal.hms.model.Doctor;
import com.personal.hms.repository.DoctorRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DoctorDataFetcher implements DataFetcher<Doctor>{
	
	private final DoctorRepository dr;
	
	DoctorDataFetcher(DoctorRepository dr) {
		this.dr = dr;
	}
	
	@Override
	public Doctor get(DataFetchingEnvironment env) {
		Map<String, Object> args = env.getArguments();
		Optional<Doctor> user = dr.findById(String.valueOf(args.get("id")));
        return user.get();
	}
}

