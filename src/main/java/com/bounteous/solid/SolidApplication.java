package com.bounteous.solid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SolidApplication {
	public static void main(String[] args) {
		SpringApplication.run(SolidApplication.class, args);
	}
}
