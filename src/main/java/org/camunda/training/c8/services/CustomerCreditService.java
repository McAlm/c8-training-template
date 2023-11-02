package org.camunda.training.c8.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CustomerCreditService {


    private final BigDecimal ZERO = new BigDecimal(0.0);

    public Map<String, BigDecimal> getRemainingAmount(String customerId, BigDecimal orderTotal) {
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

        return Map.of("amount", remainingAmount, "credit", credit);
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
