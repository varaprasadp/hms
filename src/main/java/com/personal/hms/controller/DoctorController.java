package com.personal.hms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.hms.model.Doctor;
import com.personal.hms.repository.DoctorRepository;
import com.personal.hms.utilities.GraphQLUtility;

import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
public class DoctorController {
	@Autowired
	DoctorRepository dr;

	private GraphQL graphQL;
	private GraphQLUtility graphQlUtility;

	@Autowired
	DoctorController(GraphQLUtility graphQlUtility) throws IOException {
		this.graphQlUtility = graphQlUtility;
		graphQL = graphQlUtility.createGraphQLObject();
	}

	@PostMapping("/query")
	public ResponseEntity query(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		System.out.println("errors: " + result.getErrors());
		return ResponseEntity.ok(result.getData());
	}


	@PostMapping("createDocs")
	public void create(@RequestBody Doctor drDoctor) {
		dr.save(drDoctor);
	}

	@GetMapping("getDocs")
	public void getDoc() {
		dr.findAll().forEach(doc -> System.out.println(doc.toString()));
	}

//	@PostMapping("getDocsByAnyField")
//	public void getDocsByAnyField(@RequestBody Doctor doc) {
//		dr.find(doc.getSpecialization()).forEach(d -> System.out.println(d.toString()));
//	}
}
