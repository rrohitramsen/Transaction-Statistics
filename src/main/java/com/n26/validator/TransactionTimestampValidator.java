package com.n26.validator;

import com.n26.exception.FieldsNotParsableException;
import com.n26.exception.FutureTransactionException;
import com.n26.exception.OlderTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class TransactionTimestampValidator implements ConstraintValidator<TransactionTimestamp, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTimestampValidator.class);

    public static final int SIXTY_SECONDS_IN_MILLIS = 60000;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date inputDate = dateFormat.parse(value);
            Long currentTimeInMillis = Instant.now().toEpochMilli();
            Long inputTimeInMillis = inputDate.getTime();

            LOGGER.info("currentTimeInMillis = "+currentTimeInMillis+" and inputTimeInMillis = "+inputTimeInMillis);

            if (inputTimeInMillis < currentTimeInMillis) {

                if (currentTimeInMillis - inputTimeInMillis > SIXTY_SECONDS_IN_MILLIS)  {
                    LOGGER.info("Transaction should not be older than 60 seconds");
                    throw new OlderTransactionException("Transaction should not be older than 60 seconds");
                }

            }else {
                LOGGER.info("Time should not be current or future");
                throw new FutureTransactionException("Time should not be current or future");
            }

        } catch (ParseException e) {
            LOGGER.info("FieldsNotParsableException");
            throw new FieldsNotParsableException(e.getMessage());
        }

        return true;
    }
}
