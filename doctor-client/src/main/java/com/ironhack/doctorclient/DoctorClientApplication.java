package com.ironhack.doctorclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DoctorClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorClientApplication.class, args);
	}

}
