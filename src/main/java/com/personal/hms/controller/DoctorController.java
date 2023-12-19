package com.personal.hms.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.hms.utilities.GraphQLUtility;

import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
public class DoctorController {

	private GraphQL graphQL;

	DoctorController(GraphQLUtility graphQlUtility) throws IOException {
		graphQL = graphQlUtility.createGraphQLObject();
	}

	@PostMapping("/query")
	public ResponseEntity query(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		if(result.getErrors().size() > 0) {
			System.out.println("errors: " + result.getErrors());
			 
		}
		return ResponseEntity.ok(result.getData());
	}

}
