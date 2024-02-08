package com.example.fhir.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fhir.demo.service.FHIRManipulationService;

@SpringBootApplication
public class FhirApplication implements CommandLineRunner {

	@Autowired
	private FHIRManipulationService service;


	public static void main(String[] args) {
		SpringApplication.run(FhirApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.manipulateResource();
	}
}
