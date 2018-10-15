package com.n26.validator;

import com.n26.exception.FieldValidatorException;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AmountValidatorTest {

    private ConstraintValidatorContextImpl constraintValidatorContextImpl;
    private AmountValidator amountValidator;

    @Before
    public void setUp() throws Exception {

        constraintValidatorContextImpl = Mockito.mock(ConstraintValidatorContextImpl.class);
        amountValidator = new AmountValidator();
    }

    @Test(expected = FieldValidatorException.class)
    public void isValidWhenAmountIsNotBigDecimal() {

        amountValidator.isValid("abcd", constraintValidatorContextImpl);
    }

    @Test
    public void isValid() {

        Assert.assertEquals(true, amountValidator.isValid("20.345", constraintValidatorContextImpl));
    }
}