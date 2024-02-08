# Java + FHIR Project

This is a Java project that demonstrates how to interact with FHIR (Fast Healthcare Interoperability Resources) using the HAPI FHIR library. It includes examples of reading, creating, updating, and saving FHIR resources.

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

- Java Development Kit (JDK) installed
- Apache Maven installed
- FHIR server (e.g., HAPI FHIR Server) accessible (required only if you intend to test saving to a FHIR server; otherwise, follow the instructions below to save to Google Cloud Firestore).

  
## Building APIs to Save FHIR Resources

If you want to build APIs to save FHIR resources to Google Cloud Firestore, you can follow these general steps:

1. **Set Up Google Cloud Project**: Create a Google Cloud project and enable the Firestore API.

2. **Authenticate Your Application**: Obtain the necessary credentials to authenticate your application with Google Cloud Firestore. You can use service account credentials or other authentication methods supported by Google Cloud.

3. **Use Cloud Firestore SDK**: Integrate the Cloud Firestore SDK into your Java application. You can add the Firestore dependency to your Maven project and use the Firestore client library to interact with the Firestore database.

4. **Define API Endpoints**: Design and implement API endpoints in your Java application to handle CRUD operations (Create, Read, Update, Delete) for FHIR resources. These endpoints should handle incoming requests, validate input data, and interact with Cloud Firestore to save and retrieve FHIR resources.

5. **Serialize FHIR Resources**: Before saving FHIR resources to Cloud Firestore, serialize them to JSON format using a library like HAPI FHIR. This ensures that the resources are compatible with Firestore's data format.

6. **Save Resources to Firestore**: Use the Firestore client library to save the serialized FHIR resources to Firestore collections. Map each FHIR resource type to a Firestore collection, and store individual resources as documents within those collections.

7. **Handle Errors and Security**: Implement error handling and security measures in your API endpoints to ensure data integrity and protect against unauthorized access. Use Firestore security rules to control access to resources and enforce data validation rules.

8. **Testing and Deployment**: Test your API endpoints thoroughly to ensure they function as expected. Once testing is complete, deploy your application to your preferred hosting environment, such as Google Cloud App Engine or Kubernetes Engine.

For detailed documentation and examples on using Google Cloud Firestore with Java, refer to the [Google Cloud Firestore documentation](https://cloud.google.com/firestore/docs/).


### Installing

1. Clone the repository to your local machine.

```
git clone https://github.com/your-username/java-fhir-project.git
```

2. Navigate to the project directory.

```
cd java-fhir-project
```

3. Build the project using Maven.

```
mvn clean install
```

### Running the Project

1. Start the FHIR server if not already running.

2. Run the project.

```
mvn exec:java -Dexec.mainClass="com.example.fhir.demo.FhirApplication"
```

## Maven Dependencies

The following dependencies are required in the `pom.xml` file:

```xml
<dependencies>
		<dependency>
			<groupId>ca.uhn.hapi.fhir</groupId>
			<artifactId>hapi-fhir-spring-boot-starter</artifactId>
			<version>6.4.4</version>
		</dependency>
		<dependency>
			<groupId>ca.uhn.hapi.fhir</groupId>
			<artifactId>hapi-fhir-structures-r4</artifactId>
			<version>6.4.4</version>
		</dependency>
    <!-- Add any other dependencies here -->
</dependencies>
```

## Usage

### Manipulating Resources

The project demonstrates how to manipulate FHIR resources using the HAPI FHIR library. Key operations include:

- **Reading a Resource**: Read a FHIR resource from a JSON file.
- **Creating a Resource**: Create a new FHIR resource (e.g., Patient) with specified attributes.
- **Updating a Resource**: Update an existing FHIR resource (e.g., DiagnosticReport) by linking it to another resource.
- **Saving a Resource**: Save a FHIR resource to the FHIR server / not demonstrated.

### Example

```java
FHIRManipulationService service = new FHIRManipulationService();

// Read a resource from a JSON file
service.manipulateResource();
```

## Authors

- Developer : Aparajita Sengupta - sengupta.aparajita@bcg.com
- PM: Shai Dinnar 

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
