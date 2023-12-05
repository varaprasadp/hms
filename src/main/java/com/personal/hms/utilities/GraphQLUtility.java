package com.personal.hms.utilities;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.personal.hms.dataFetchers.DoctorDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;

@Component
public class GraphQLUtility {
	@Value("classpath:graphql/schemas.graphqls")
	private Resource schemaResource;
	private GraphQL graphQL;
	private DoctorDataFetcher doctorDataFetcher;
	
	@Autowired
	GraphQLUtility(DoctorDataFetcher ddf) throws IOException {
		doctorDataFetcher = ddf;
	}
	
	@PostConstruct
	public GraphQL createGraphQLObject() throws IOException {
		File schemas = schemaResource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		return GraphQL.newGraphQL(schema).build();
	}
	
	public RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("doctor", doctorDataFetcher)).build();
	}
}
