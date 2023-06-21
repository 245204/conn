package com.ust.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MusicUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicUserApplication.class, args);
	}

}
