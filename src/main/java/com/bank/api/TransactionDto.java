package com.bank.api;

import com.bank.validator.Amount;
import com.bank.validator.TransactionTimestamp;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author rohitkumar
 */
public class TransactionDto {

    @ApiModelProperty(notes = "transaction amount; a string of arbitrary length that is parsable as a BigDecimal.")
    @Amount
    private String amount;

    @ApiModelProperty(notes = "transaction time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.sssZ in the UTC timezone (this is not the current timestamp).")
    @TransactionTimestamp
    private String  timestamp;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
