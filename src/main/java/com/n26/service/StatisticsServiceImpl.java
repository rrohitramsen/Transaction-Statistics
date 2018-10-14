package com.n26.service;

import com.n26.api.TransactionController;
import com.n26.entity.Statistics;
import com.n26.entity.Transaction;
import com.n26.exception.StatisticsNotAvailableException;
import com.n26.helper.BigDecimalSummaryStatistics;
import com.n26.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Statistics getLastSixtySecondsStatistics() {

        LOGGER.info("Inside getLastSixtySecondsStatistics.");

        Map<Transaction, Long> allTransactions = transactionRepository.getAllTransactions();

        if (allTransactions == null || allTransactions.isEmpty()) {
            throw new StatisticsNotAvailableException("Statics not available for the current 60 seconds.");
        }

        Statistics statistics = buildLastSixtySecondsStatistics(allTransactions);
        return statistics;
    }

    private Statistics buildLastSixtySecondsStatistics(Map<Transaction, Long> allTransactions) {

       Set<Transaction> sixtySecondTransactions = getAllTransactionsInSixtSecondRange(allTransactions);

        BigDecimalSummaryStatistics bigDecimalSummaryStatistics = sixtySecondTransactions
                .stream()
                .map(transaction -> transaction.getAmount())
                .collect(BigDecimalSummaryStatistics.statistics());

        LOGGER.debug("Big Decimal Summary Statistics,"+bigDecimalSummaryStatistics);

        Statistics statistics = new Statistics();
        statistics.setAvg(bigDecimalSummaryStatistics.getAvg());
        statistics.setCount(bigDecimalSummaryStatistics.getCount());
        statistics.setMax(bigDecimalSummaryStatistics.getMax());
        statistics.setMin(bigDecimalSummaryStatistics.getMin());
        statistics.setSum(bigDecimalSummaryStatistics.getSum());

        LOGGER.info("Returning last sixty second statistics."+statistics);

        return statistics;

    }

    private Set<Transaction> getAllTransactionsInSixtSecondRange(Map<Transaction, Long> allTransactions) {

        Set<Transaction> transactions = allTransactions.keySet();
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();
        Long timeLimitOfSixtySeconds = currentTime - 60000;
        transactions.removeIf(transaction -> transaction.getTimestamp().getTime() < timeLimitOfSixtySeconds);

        return transactions;
    }


}
