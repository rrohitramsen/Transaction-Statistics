package com.n26.api;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author rohitkumar
 */
public class RequestBody {

    @ApiModelProperty(notes = "transaction amount; a string of arbitrary length that is parsable as a BigDecimal.")
    private String amount;

    @ApiModelProperty(notes = "transaction time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.sssZ in the UTC timezone (this is not the current timestamp).")
    private String timestamp;

    public RequestBody(String amount, String timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

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
