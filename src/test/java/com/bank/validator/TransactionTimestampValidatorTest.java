package com.bank.validator;

import com.bank.exception.FieldValidatorException;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class TransactionTimestampValidatorTest {

    private ConstraintValidatorContextImpl constraintValidatorContextImpl;
    private TransactionTimestampValidator transactionTimestampValidator;

    @Before
    public void setUp() throws Exception {
        constraintValidatorContextImpl = Mockito.mock(ConstraintValidatorContextImpl.class);
        transactionTimestampValidator = new TransactionTimestampValidator();
    }

    @Test(expected = FieldValidatorException.class)
    public void testIsValidWhenTimestampIsNotInISO8601Format() {

        String timestamp = "4/23/2018 11:32 PM";
        transactionTimestampValidator.isValid(timestamp, constraintValidatorContextImpl);
    }

    @Test(expected = FieldValidatorException.class)
    public void testIsValidWhenTimestampIsOlderThen60Seconds() {

        String timestamp = "2018-07-17T09:59:51.312Z";
        transactionTimestampValidator.isValid(timestamp, constraintValidatorContextImpl);
    }

    @Test(expected = FieldValidatorException.class)
    public void testIsValidWhenTimestampIsInFuture() {

        String timestamp = "2018-11-17T09:59:51.312Z";
        transactionTimestampValidator.isValid(timestamp, constraintValidatorContextImpl);
    }

    @Test
    public void testIsValid() {

        Instant instant = Instant.now().plusMillis(-20000);
        String timestamp = DateTimeFormatter.ISO_INSTANT.format(instant);
        boolean actual = transactionTimestampValidator.isValid(timestamp, constraintValidatorContextImpl);

        Assert.assertEquals(true, actual);
    }
}