package org.camunda.training.c8.worker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.camunda.training.c8.dto.ChargeCreditCardData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;

@Component
public class ChargeCreditCardWorker {

    Logger LOG = LoggerFactory.getLogger(ChargeCreditCardWorker.class);

    @JobWorker(type = "chargeCreditCard", //
            autoComplete = false, //
            fetchVariables = { "cardNumber", "cvc", "expiryDate", "remainingAmount" })
    public void chargeCreditCard(JobClient jobClient, ActivatedJob job, @VariablesAsType ChargeCreditCardData cccData) {
        if (cardExpired(cccData.getExpiryDate())) {
            LOG.error("Credit Card has expired!");
            jobClient//
                    .newFailCommand(job)//
                    .retries(0)
                    //retries do not make sense here since a retry would not change the expiryDate
                    //.retries(job.getRetries() - 1)//
                    //.retryBackoff(Duration.ofSeconds(5))
                    .errorMessage("Credit Card has expired!")//
                    .send()//
                    .exceptionally((throwable -> {
                        throw new RuntimeException("Could not send job failure", throwable);
                    }));
        } else {
            chargeAmount(cccData);
            jobClient.newCompleteCommand(job).send().exceptionally((throwable -> {
                throw new RuntimeException("Could not complete job", throwable);
            }));
        }
    }

    private boolean cardExpired(String expiryDate) {

        SimpleDateFormat mmyy = new SimpleDateFormat("MM/yy");
        SimpleDateFormat mmyyyy = new SimpleDateFormat("MM/yyyy");

        Date date = null;
        try {
            date = mmyy.parse(expiryDate);

        } catch (ParseException e) {
            try {
                date = mmyyyy.parse(expiryDate);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return true;
            }
        }

        if (date != null) {
            Calendar expiryCal = Calendar.getInstance();
            expiryCal.setTime(date);
            Calendar today = Calendar.getInstance();
            if (today.after(expiryCal)) {
                return true;
            }
        }
        return false;
    }

    private void chargeAmount(ChargeCreditCardData cccData) {
        LOG.info("charging CreditCard: " + cccData);
    }
}
