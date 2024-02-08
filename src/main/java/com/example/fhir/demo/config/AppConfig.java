package com.example.fhir.demo.config;


import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    @Primary
    public ObjectMapper primaryObjectMapper() {
        return JsonMapper.builder() //.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModule(new JavaTimeModule()).build();
    }

    @Bean
    public FhirContext r4fhirContext() {
        return FhirContext.forR4();
    }

}
