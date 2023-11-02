package org.camunda.training.c8.worker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class TestWorker {
    

    @Autowired
    private TestService ts;

    @JobWorker(type="testService", fetchVariables = "testInput")
    public Map<String, Object> test(@Variable String testInput){

        String upper = ts.serviceTest(testInput);
        return Map.of("upperCaseOutput", upper);

    }
}
