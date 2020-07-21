package com.ironhack.evaluationclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EvaluationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluationClientApplication.class, args);
	}

}
