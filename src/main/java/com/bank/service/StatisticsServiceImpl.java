package com.bank.service;

import com.bank.entity.Statistics;
import com.bank.entity.Transaction;
import com.bank.helper.BigDecimalSummaryStatistics;
import com.bank.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    public static final int SCALE = 2;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Statistics getLastSixtySecondsStatistics() {

        LOGGER.info("Inside getLastSixtySecondsStatistics.");

        Map<Transaction, Long> allTransactions = transactionRepository.getAllTransactions();

        if (allTransactions == null || allTransactions.isEmpty()) {
            return new Statistics();
        }

        Statistics statistics = buildLastSixtySecondsStatistics(allTransactions);
        return statistics;
    }

    private Statistics buildLastSixtySecondsStatistics(Map<Transaction, Long> allTransactions) {

       Set<Transaction> sixtySecondTransactions = getAllTransactionsInSixtySecondRange(allTransactions);

        BigDecimalSummaryStatistics bigDecimalSummaryStatistics = sixtySecondTransactions
                .stream()
                .map(transaction -> transaction.getAmount())
                .collect(BigDecimalSummaryStatistics.statistics());

        LOGGER.debug("Big Decimal Summary Statistics,"+bigDecimalSummaryStatistics);

        Statistics statistics = buildStatisticsFromBigDecimalStatistics(bigDecimalSummaryStatistics);

        LOGGER.info("Returning last sixty second statistics."+statistics);

        return statistics;

    }

    private Statistics buildStatisticsFromBigDecimalStatistics(BigDecimalSummaryStatistics bigDecimalSummaryStatistics) {

        Statistics statistics = new Statistics();

        Object avg = bigDecimalSummaryStatistics.getAvg();
        statistics.setAvg(((BigDecimal) avg).setScale(SCALE, BigDecimal.ROUND_HALF_UP).toString());

        statistics.setCount(bigDecimalSummaryStatistics.getCount());

        Object max = bigDecimalSummaryStatistics.getMax();
        statistics.setMax(((BigDecimal) max).setScale(SCALE, BigDecimal.ROUND_HALF_UP).toString());

        Object min = bigDecimalSummaryStatistics.getMin();
        statistics.setMin(((BigDecimal) min).setScale(SCALE, BigDecimal.ROUND_HALF_UP).toString());

        Object sum = bigDecimalSummaryStatistics.getSum();
        statistics.setSum(((BigDecimal) sum).setScale(SCALE, BigDecimal.ROUND_HALF_UP).toString());
        return statistics;
    }

    private Set<Transaction> getAllTransactionsInSixtySecondRange(Map<Transaction, Long> allTransactions) {

        Set<Transaction> transactions = allTransactions.keySet();
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();
        Long timeLimitOfSixtySeconds = currentTime - 60000;
        transactions.removeIf(transaction -> transaction.getTimestamp().getTime() < timeLimitOfSixtySeconds);

        return transactions;
    }


}
