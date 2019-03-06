package com.bank.validator;

import com.bank.exception.FieldValidatorException;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @author rohitkumar
 * Amount validations.
 */
public class AmountValidator implements ConstraintValidator<Amount, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            BigDecimal amount = new BigDecimal(value);
        }catch (NumberFormatException e) {
            throw new FieldValidatorException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return true;
    }
}
