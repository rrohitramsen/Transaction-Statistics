package com.bank.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author rohitkumar
 */
public class Statistics {

    @ApiModelProperty(notes = "A BigDecimal specifying the total sum of transaction value in the last 60 seconds.")
    private String sum;

    @ApiModelProperty(notes = "A BigDecimal specifying the average amount of transaction value in the last 60 seconds.")
    private String avg;

    @ApiModelProperty(notes = "A BigDecimal specifying single highest transaction value in the last 60 seconds.")
    private String max;

    @ApiModelProperty(notes = "A BigDecimal specifying single lowest transaction value in the last 60 seconds.")
    private String min;

    @ApiModelProperty(notes = "A long specifying the total number of transactions that happened in the last 60 seconds.")
    private long count;

    public Statistics() {
        initializeDefaultValues();
    }

    private void initializeDefaultValues() {
        sum = "0.00";
        avg = "0.00";
        max = "0.00";
        min = "0.00";
        count = 0;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "sum='" + sum + '\'' +
                ", avg='" + avg + '\'' +
                ", max='" + max + '\'' +
                ", min='" + min + '\'' +
                ", count=" + count +
                '}';
    }
}
