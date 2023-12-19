package com.personal.hms.dataFetchers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.personal.hms.model.Consultation;
import com.personal.hms.model.Doctor;
import com.personal.hms.repository.ConsultationRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ConsultationsDataFetcher implements DataFetcher<List<Consultation>>{

	private final ConsultationRepository cr;
	
	ConsultationsDataFetcher(ConsultationRepository cr) {
		this.cr = cr;
	}

	@Override
	public List<Consultation> get(DataFetchingEnvironment env) throws Exception {
//		Map<String, Object> args = env.getArguments();
		Doctor d = env.getSource();
		System.out.println(d);
//		List<Consultation> users = cr.find(String.valueOf(d.getId()));
//		List<Consultation> users = cr.find(String.valueOf(d.getId()), String.valueOf(args.get("patientID")));
        return cr.findAll();
	}
	
	
}