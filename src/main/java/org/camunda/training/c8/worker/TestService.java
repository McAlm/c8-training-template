package org.camunda.training.c8.worker;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String serviceTest(String testInput){
        System.out.println(" Huhuuu..."  + testInput);
        return testInput.toUpperCase();
    }
}
