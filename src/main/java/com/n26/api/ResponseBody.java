package com.n26.api;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author rohitkumar
 */
public class ResponseBody {

    @ApiModelProperty(notes = "A BigDecimal specifying the total sum of transaction value in the last 60 seconds.")
    private BigDecimal sum;

    @ApiModelProperty(notes = "A BigDecimal specifying the average amount of transaction value in the last 60 seconds.")
    private BigDecimal avg;

    @ApiModelProperty(notes = "A BigDecimal specifying single highest transaction value in the last 60 seconds.")
    private BigDecimal max;

    @ApiModelProperty(notes = "A BigDecimal specifying single lowest transaction value in the last 60 seconds.")
    private BigDecimal min;

    @ApiModelProperty(notes = "A long specifying the total number of transactions that happened in the last 60 seconds.")
    private long count;

    public ResponseBody(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
