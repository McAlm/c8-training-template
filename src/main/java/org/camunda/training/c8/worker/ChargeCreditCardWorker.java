package org.camunda.training.c8.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class ChargeCreditCardWorker {

    Logger LOG = LoggerFactory.getLogger(ChargeCreditCardWorker.class);

    @JobWorker(type = "chargeCreditCard", autoComplete = true)
    public void chargeCreditCard() {
        LOG.info("charging credit card...");
    }

}
