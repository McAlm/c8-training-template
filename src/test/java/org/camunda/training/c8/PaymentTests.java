package org.camunda.training.c8;

import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.camunda.training.c8.services.CustomerCreditService;
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

@ZeebeSpringTest
@SpringBootTest
public class PaymentTests {

	@Autowired
	protected ZeebeTestEngine engine;

	@Autowired
	protected ZeebeClient client;

	@MockBean
	private CustomerCreditService ccs;

	@Test
	public void testHappyPath() throws InterruptedException, TimeoutException {

		BigDecimal orderTotal = new BigDecimal(45.00);
		Mockito.when(ccs.getRemainingAmount("cust30", orderTotal))//
				.thenReturn(//
						Map.of("amount", new BigDecimal(15.00), //
								"credit", new BigDecimal(0.0)));

		Map<String, Object> vars = new HashMap<>();

		vars.put("orderTotal", orderTotal);
		vars.put("customerId", "cust30");
		vars.put("orderId", "order-1");
		vars.put("cardNumber", "1234 5678");
		vars.put("cvc", "123");
		vars.put("expiryDate", "11/24");

		ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()//
				.bpmnProcessId("PaymentProcess")//
				.latestVersion()//
				.variables(vars)//
				.send()//
				.join();

		BpmnAssert.assertThat(processInstance).isStarted();

		waitForProcessInstanceHasPassedElement(processInstance, "DeductCreditTask");
		waitForProcessInstanceHasPassedElement(processInstance, "ChargeCreditCardTask");
		waitForProcessInstanceHasPassedElement(processInstance, "PayedEndEvent");

		BpmnAssert.assertThat(processInstance)
				.hasPassedElementsInOrder("DeductCreditTask", "ChargeCreditCardTask", "PayedEndEvent")
				.hasVariableWithValue("remainingAmount", 15)
				.hasVariableWithValue("customerCredit", 0)
				.isCompleted();
	}

}
