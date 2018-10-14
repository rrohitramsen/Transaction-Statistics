package com.n26.entity;

import com.n26.validator.IsOlderThen60Seconds;
import com.n26.validator.NotFutureOrCurrentDate;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author rohitkumar
 */
public class Transaction implements Comparable<Transaction>{

    @NotNull(message = "amount is a required field")
    @DecimalMin(value = "0", message = "amount is a required field")
    @ApiModelProperty(notes = "transaction amount; a string of arbitrary length that is parsable as a BigDecimal.")
    private BigDecimal amount;

    @NotNull(message = "timestamp is a required field")
    @NotFutureOrCurrentDate(message = "Time should not be current or future")
    @IsOlderThen60Seconds(message = "Transaction should not be older than 60 seconds.")
    @ApiModelProperty(notes = "transaction time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.sssZ in the UTC timezone (this is not the current timestamp).")
    private Date timestamp;

    public Transaction() {
    }

    public Transaction(@NotNull @NotEmpty BigDecimal amount, @NotNull @NotEmpty Date timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, timestamp);
    }

    @Override
    public int compareTo(Transaction transaction) {
        return transaction.getTimestamp().compareTo(this.timestamp);
    }
}
