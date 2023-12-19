package com.personal.hms.utilities;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.personal.hms.dataFetchers.ConsultationsDataFetcher;
import com.personal.hms.dataFetchers.DoctorDataFetcher;
import com.personal.hms.dataFetchers.DoctorsDataFetcher;

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
	private DoctorDataFetcher doctorDataFetcher;
	private DoctorsDataFetcher doctorsDataFetcher;
	private ConsultationsDataFetcher consultationsDataFetcher;
	
	GraphQLUtility(DoctorDataFetcher ddf, DoctorsDataFetcher dsdf, ConsultationsDataFetcher csdf) throws IOException {
		doctorDataFetcher = ddf;
		doctorsDataFetcher = dsdf;
		consultationsDataFetcher = csdf;
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
						.dataFetcher("doctor", doctorDataFetcher).dataFetcher("doctors", doctorsDataFetcher))
				.type("Doctor", typeWiring -> typeWiring
						.dataFetcher("consultations", consultationsDataFetcher)).build();
	}
}
