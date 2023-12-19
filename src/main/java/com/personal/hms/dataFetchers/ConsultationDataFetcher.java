package com.personal.hms.dataFetchers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personal.hms.model.Consultation;
import com.personal.hms.repository.ConsultationRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ConsultationDataFetcher implements DataFetcher<Consultation>{

	private final ConsultationRepository cr;
	
	@Autowired
	ConsultationDataFetcher(ConsultationRepository cr) {
		this.cr = cr;
	}
	
	@Override
	public Consultation get(DataFetchingEnvironment env) {
		Map<String, Object> args = env.getArguments();
		Optional<Consultation> user = cr.findById(String.valueOf(args.get("id")));
        return user.get();
	}
}
