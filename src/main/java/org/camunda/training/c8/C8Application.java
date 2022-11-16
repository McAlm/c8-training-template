package org.camunda.training.c8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath:bpmn/payment.bpmn")
public class C8Application {

	public static void main(String[] args) {
		SpringApplication.run(C8Application.class, args);
	}

}