package org.camunda.training.c8.worker;

import org.camunda.training.c8.dto.ChargeCreditCardData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;

@Component
public class ChargeCreditCardWorker {

    Logger LOG = LoggerFactory.getLogger(ChargeCreditCardWorker.class);

    @JobWorker(type = "chargeCreditCard", //
            autoComplete = true, //
            fetchVariables = { "cardNumber", "cvc", "expiryDate", "remainingAmount" })
    public void deductCredit(@VariablesAsType ChargeCreditCardData cccData) {
        chargeAmount(cccData);
    }

    private void chargeAmount(ChargeCreditCardData cccData) {
        LOG.info("charging CreditCard: " + cccData);
    }
}
