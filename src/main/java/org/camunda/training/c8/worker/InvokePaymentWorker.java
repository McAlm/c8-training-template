package org.camunda.training.c8.worker;

import org.camunda.training.c8.dto.ChargeCreditCardData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;

@Component
public class InvokePaymentWorker {

    private final Logger LOG = LoggerFactory.getLogger(InvokePaymentWorker.class);

    @Autowired
    private ZeebeClient zeebeClient;

    @JobWorker(autoComplete = true, fetchVariables = { "orderId", "cardNumber", "cvc", "expiryDate",
            "remainingAmount", "customerId", "orderTotal" })
    public void invokePayment(@VariablesAsType ChargeCreditCardData cccData) {
        LOG.info("invoking payment process for orderId " + cccData.getOrderId());
        zeebeClient.newPublishMessageCommand()//
                .messageName("paymentRequestMessage")//
                .correlationKey(cccData.getOrderId())//
                .variables(cccData)//
                .send();
    }
}
