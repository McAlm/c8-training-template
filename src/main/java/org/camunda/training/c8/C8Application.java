package org.camunda.training.c8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient
public class C8Application {

	public static void main(String[] args) {
		SpringApplication.run(C8Application.class, args);
	}

}