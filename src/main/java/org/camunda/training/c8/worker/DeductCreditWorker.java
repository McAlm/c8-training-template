package org.camunda.training.c8.worker;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class DeductCreditWorker {
    
    Logger LOG = LoggerFactory.getLogger(DeductCreditWorker.class);

    @JobWorker(type = "deductCredit", autoComplete = true, fetchVariables = "orderTotal")
    public void deductCredit(@Variable BigDecimal orderTotal){
        LOG.info("deducting credit..." + orderTotal.toPlainString());
    }
}
