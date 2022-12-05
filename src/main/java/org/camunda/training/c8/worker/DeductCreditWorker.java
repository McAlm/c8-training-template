package org.camunda.training.c8.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class DeductCreditWorker {
    
    Logger LOG = LoggerFactory.getLogger(DeductCreditWorker.class);

    @JobWorker(type = "deductCredit", autoComplete = true)
    public void deductCredit(){
        LOG.info("deducting credit...");
    }
}
