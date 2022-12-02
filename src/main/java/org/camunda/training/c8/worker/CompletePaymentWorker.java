package org.camunda.training.c8.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class CompletePaymentWorker {

    private final Logger LOG = LoggerFactory.getLogger(CompletePaymentWorker.class);

    @Autowired
    private ZeebeClient zeebeClient;

    @JobWorker(autoComplete = true, fetchVariables = "orderId")
    public void completePayment(@Variable String orderId) {
        LOG.info("complete Payment for OrderId " + orderId);
        zeebeClient.newPublishMessageCommand()//
                .messageName("paymentCompletedMessage")//
                .correlationKey(orderId)//
                .send();
    }
}
