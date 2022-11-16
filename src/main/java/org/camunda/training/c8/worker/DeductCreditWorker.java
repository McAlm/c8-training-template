package org.camunda.training.c8.worker;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class DeductCreditWorker {

    Logger LOG = LoggerFactory.getLogger(DeductCreditWorker.class);
    private final BigDecimal ZERO = new BigDecimal(0.0);

    @JobWorker(type = "deductCredit", autoComplete = true, fetchVariables = { "orderTotal", "customerId" })
    public Map<String, Object> deductCredit(@Variable BigDecimal orderTotal, @Variable String customerId) {
        LOG.info("deducting credit for customerId " + customerId + " and orderTotal " + orderTotal.toPlainString());

        BigDecimal credit = getCustomerCredit(customerId);

        BigDecimal difference = credit.subtract(orderTotal);
        BigDecimal remainingAmount = ZERO;

        if (difference.compareTo(ZERO) <= 0) {
            remainingAmount = difference.abs();
            credit = ZERO;
        } else {
            remainingAmount = ZERO;
            credit = difference;
        }

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("remainingAmount", remainingAmount);
        variables.put("customerCredit", credit);
        return variables;

    }

    private BigDecimal getCustomerCredit(String customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("customerId must be provided to get the customer credit");
        }

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(customerId);
        String creditStringFromCustomerId = "0.0";

        while (m.find()) {
            // we take the last group of digits to simulate the customers credit
            creditStringFromCustomerId = m.group();
        }
        return new BigDecimal(creditStringFromCustomerId);
    }
}
