package com.student.studentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StudentmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmsApplication.class, args);
	}

}
