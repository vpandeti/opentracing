package com.pansource.opentracing.sampleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleserviceApplication.class, args);
    }

    // REQUIRED: Sleuth automatically adds interceptors for RestTemplate calls
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
