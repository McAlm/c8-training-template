package org.camunda.training.c8.worker;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.camunda.training.c8.services.CustomerCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class DeductCreditWorker {

    @Autowired
    private CustomerCreditService ccs;

    Logger LOG = LoggerFactory.getLogger(DeductCreditWorker.class);

    @JobWorker(type = "deductCredit", autoComplete = true, fetchVariables = { "orderTotal", "customerId" })
    public Map<String, Object> deductCredit(@Variable BigDecimal orderTotal, @Variable String customerId) {
        LOG.info("deducting credit for customerId " + customerId + " and orderTotal " + orderTotal.toPlainString());

        Map<String, BigDecimal> ccsOutMap = ccs.getRemainingAmount(customerId, orderTotal);

        Map<String, Object> variables = new HashMap<>();
        variables.put("remainingAmount", ccsOutMap.get("amount"));
        variables.put("customerCredit", ccsOutMap.get("credit"));
        return variables;

    }
}
