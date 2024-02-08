package com.example.fhir.demo.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class FHIRManipulationService {

    private static final Logger logger = LoggerFactory.getLogger(FHIRManipulationService.class);

    // FHIR Context's parser directly
    private FhirContext fhirContext =  FhirContext.forR4();


    public void manipulateResource() {
        try {
            // Read a resource from json file
            String jsonContent = readResource("mockdata/mock_diagnostic_report.json");
            DiagnosticReport mockDiagnosticReport = fhirContext.newJsonParser().parseResource(DiagnosticReport.class, jsonContent);
            logger.info("Successfully read DiagnosticReport ({}) from JSON file.", fhirContext.newJsonParser().encodeResourceToString(mockDiagnosticReport));

           // Create a  new resource
            Resource mockPatient  = createResource();
            logger.info("Successfully created resource Patient: {} from JSON file.", fhirContext.newJsonParser().encodeResourceToString(mockPatient));

            // Update an existing resource / reference
            DiagnosticReport updatedDiagnosticReport = updateResource(mockDiagnosticReport,mockPatient);
            logger.info("Successfully updated resource DiagnosticReport: {} from JSON file.", fhirContext.newJsonParser().encodeResourceToString(updatedDiagnosticReport));

        } catch (IOException e) {
            logger.error("Error reading DiagnosticReport from JSON file:", e);
        }
    }

    /***
     * Method to read a resource
     * @param file path
     * @return resource body
     *
     */
    public static String readResource(String file) throws IOException {
        Path resourceDirectory = Paths.get("src", "main", "resources", file);
        Scanner scanner = new Scanner(resourceDirectory.toFile());
        String body = scanner.useDelimiter("\\A").next();
        return body;
    }


    public Resource createResource() {
        // Create a new Patient resource
        Patient patient = new Patient();

        // Setting patient attributes
        patient.addIdentifier().setSystem("http://example.com/patient-identifier").setValue("12345");
        patient.setId("New ID");
        patient.addName().setFamily("Doe").addGiven("John");
        patient.setGender(Enumerations.AdministrativeGender.MALE); // Set patient gender
        patient.setBirthDateElement(new DateType("1980-01-01")); // Set patient birth date

        // Add more attributes as needed
        logger.info("Successfully created Patient: {}", patient.getId());
        return patient;
    }


    public DiagnosticReport updateResource(Resource diagnosticReport, Resource patient) {
        try {
            // Update the DiagnosticReport with a reference to the Patient
            Reference patientRef = new Reference("Patient/" + patient.getId());
            ((DiagnosticReport) diagnosticReport).setSubject(patientRef);

            // Encode the updated DiagnosticReport to JSON
            String updatedDrJson = fhirContext.newJsonParser().encodeResourceToString(diagnosticReport);

            logger.info("Successfully updated DiagnosticReport (ID: {}) with Patient link (ID: {})", diagnosticReport.getId(), patient.getId());

            // Return the updated DiagnosticReport
            return (DiagnosticReport) diagnosticReport;
        } catch (Exception e) {
            logger.error("Error updating DiagnosticReport with Patient link:", e);
            return null;
        }
    }

    public Resource saveResource(Resource resource) {
        try {
            // Encode the resource to JSON
            String resourceJson = fhirContext.newJsonParser().encodeResourceToString(resource);

            // Make a POST request to save the resource
            IGenericClient client = fhirContext.newRestfulGenericClient("http://your-fhir-server-url");
            MethodOutcome outcome = client.create().resource(resource).execute();

            // Extract the ID of the saved resource from the outcome
            IdType resourceId = (IdType) outcome.getId();

            // Log the successful save
            logger.info("Successfully saved resource {} with ID: {}", resource.getClass().getSimpleName(), resourceId);

            // Return the saved resource
            return resource;
        } catch (Exception e) {
            logger.error("Error saving resource:", e);
            return null;
        }
    }
}

