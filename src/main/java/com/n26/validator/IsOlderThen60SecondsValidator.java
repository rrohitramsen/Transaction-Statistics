package com.n26.validator;

import com.n26.exception.OlderTransactionException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class IsOlderThen60SecondsValidator implements ConstraintValidator<IsOlderThen60Seconds, Date> {

    public static final int SIXTY_SECONDS_IN_MILLIS = 60000;

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        Date currentTime = new Date();
        boolean isOlderThen60Seconds = false;
        if (value.getTime() < currentTime.getTime()) {

            isOlderThen60Seconds = currentTime.getTime() - value.getTime() > SIXTY_SECONDS_IN_MILLIS;

            if (isOlderThen60Seconds)  {
                throw new OlderTransactionException("Transaction should not be older than 60 seconds");
            } else {
                return !isOlderThen60Seconds;
            }

        }
        return isOlderThen60Seconds;

    }
}
