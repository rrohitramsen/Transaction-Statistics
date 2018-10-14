package com.n26.helper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 *
 */
public class BigDecimalSummaryStatistics implements Consumer<BigDecimal> {

    public static Collector<BigDecimal,?,BigDecimalSummaryStatistics> statistics() {
        return Collector.of(BigDecimalSummaryStatistics::new,
                BigDecimalSummaryStatistics::accept, BigDecimalSummaryStatistics::merge);
    }

    MathContext twoDecimalHalRoundUp = new MathContext(2, RoundingMode.HALF_UP);
    private BigDecimal sum = new BigDecimal(0, twoDecimalHalRoundUp),
            min = new BigDecimal(0, twoDecimalHalRoundUp),
            max = new BigDecimal(0, twoDecimalHalRoundUp),
            avg = new BigDecimal(0, twoDecimalHalRoundUp);

    private long count;

    public void accept(BigDecimal t) {
        if(count == 0) {
            Objects.requireNonNull(t);
            count = 1;
            sum = t;
            min = t;
            max = t;
        }
        else {
            sum = sum.add(t);
            if(min.compareTo(t) > 0) min = t;
            if(max.compareTo(t) < 0) max = t;
            count++;
        }
    }
    public BigDecimalSummaryStatistics merge(BigDecimalSummaryStatistics s) {
        if(s.count > 0) {
            if(count == 0) {
                count = s.count;
                sum = s.sum;
                min = s.min;
                max = s.max;
            }
            else {
                sum = sum.add(s.sum);
                if(min.compareTo(s.min) > 0) min = s.min;
                if(max.compareTo(s.max) < 0) max = s.max;
                count += s.count;
            }
        }
        this.avg = getAverage(twoDecimalHalRoundUp);
        return this;
    }

    public long getCount() {
        return count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getAverage(MathContext mc) {
        return count < 2? sum: sum.divide(BigDecimal.valueOf(count), mc);
    }

    public BigDecimal getMin() {
        return min;
    }

    public BigDecimal getMax() {
        return max;
    }


    public BigDecimal getAvg() {
        return avg;
    }

    @Override
    public String toString() {
        return count == 0? "empty": ("count = "+count+", min = "+min+", max = "+max+", sum = "+sum+", avg = "+avg);
    }
}
