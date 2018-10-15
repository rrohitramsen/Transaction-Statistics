package com.n26.validator;

import com.n26.exception.FieldValidatorException;
import com.n26.helper.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.util.Date;

import static com.n26.validator.ValidatorConstants.*;

/**
 * @author rohitkumar
 *
 * {@link com.n26.entity.Transaction} validations.
 */
public class TransactionTimestampValidator implements ConstraintValidator<TransactionTimestamp, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTimestampValidator.class);


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        Date inputDate = DateUtils.parseDate(value);
        Long currentTimeInMillis = Instant.now().toEpochMilli();
        Long inputTimeInMillis = inputDate.getTime();

        LOGGER.info("currentTimeInMillis = "+currentTimeInMillis+" and inputTimeInMillis = "+inputTimeInMillis);

        if (inputTimeInMillis < currentTimeInMillis) {

            if (currentTimeInMillis - inputTimeInMillis > SIXTY_SECONDS_IN_MILLIS)  {
                LOGGER.info(TRANSACTION_OLDER_THEN_60_SECONDS);
                throw new FieldValidatorException(TRANSACTION_OLDER_THEN_60_SECONDS, HttpStatus.NO_CONTENT);
            }

        }else {
            LOGGER.info(TRANSACTION_TIME_FUTURE_OR_CURRENT);
            throw new FieldValidatorException(TRANSACTION_TIME_FUTURE_OR_CURRENT, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return true;
    }
}
