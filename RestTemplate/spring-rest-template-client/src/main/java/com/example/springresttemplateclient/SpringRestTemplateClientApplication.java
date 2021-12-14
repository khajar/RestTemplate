package com.example.springresttemplateclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.springresttemplateclient.Service.RestTemplateResponseErrorHandler;

@SpringBootApplication
public class SpringRestTemplateClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestTemplateClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
//	 @Bean
//	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//	       
//	        return builder.errorHandler(new RestTemplateResponseErrorHandler())
//	                .build();
//	    }
}
