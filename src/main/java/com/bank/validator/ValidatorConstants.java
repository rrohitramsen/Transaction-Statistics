package com.bank.validator;

/**
 * @author rohitkumar
 */
public abstract class ValidatorConstants {

    public static final String TRANSACTION_OLDER_THEN_60_SECONDS = "Transaction should not be older than 60 seconds";

    public static final int SIXTY_SECONDS_IN_MILLIS = 60000;

    public static final String TRANSACTION_TIME_FUTURE_OR_CURRENT = "Transaction time should not be current or future";

}
