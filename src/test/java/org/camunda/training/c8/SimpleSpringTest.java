package org.camunda.training.c8;

import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceCompleted;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceHasPassedElement;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.camunda.training.c8.worker.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.process.test.assertions.BpmnAssert;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;

@SpringBootTest
@ZeebeSpringTest
public class SimpleSpringTest{

    @Autowired
    protected ZeebeTestEngine engine;

    @Autowired
    protected ZeebeClient client;

    @MockBean
    private TestService ts;

    @BeforeEach
    public void setup() {
        // setEngineForCurrentThread(engine);
    }

    @Test
    public void testHappyPath() throws InterruptedException, TimeoutException {

        System.out.println("Test starts...:");
        String testInput = "aaa";
        String expectedOutput = "AAA";
        Mockito.when(ts.serviceTest(testInput)).thenReturn(expectedOutput);

        ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()//
                .bpmnProcessId("SpringProcessTestProcess")//
                .latestVersion()//
                .variables(Map.of("testInput", testInput))//
                .send()//
                .join();

        BpmnAssert.assertThat(processInstance).isStarted();

        waitForProcessInstanceHasPassedElement(processInstance, "TestTask");
        waitForProcessInstanceCompleted(processInstance.getProcessInstanceKey());

        BpmnAssert.assertThat(processInstance).hasVariableWithValue("upperCaseOutput", expectedOutput);

        Mockito.verify(ts).serviceTest(testInput);
        Mockito.verifyNoMoreInteractions(ts);
    }
}
