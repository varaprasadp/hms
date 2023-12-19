package com.personal.hms.dataFetchers;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.personal.hms.model.Doctor;
import com.personal.hms.repository.DoctorRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DoctorsDataFetcher implements DataFetcher<List<Doctor>>{
	private final DoctorRepository dr;
	
	DoctorsDataFetcher(DoctorRepository dr) {
		this.dr = dr;
	}
	
	@Override
	public List<Doctor> get(DataFetchingEnvironment env) {
//		Map<String, Object> args = env.getArguments();
		
		Set<String> fields = env.getSelectionSet().getFieldsGroupedByResultKey().keySet();
        StringBuilder stringBuilder = new StringBuilder();
		for (String field : fields) {
	           stringBuilder.append("'").append(field).append("': 1, ");
	    }

        if (fields.size() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
		List<Doctor> doctors = dr.findAll(stringBuilder.toString());
        return doctors;
	}
}
