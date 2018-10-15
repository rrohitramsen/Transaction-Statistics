package com.n26.service;

import com.n26.entity.Statistics;
import com.n26.entity.Transaction;
import com.n26.helper.BigDecimalSummaryStatistics;
import com.n26.repository.TransactionRepository;
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

        Statistics statistics = new Statistics();

        Object avg = bigDecimalSummaryStatistics.getAvg();
        statistics.setAvg(((BigDecimal) avg).setScale(2,4).toString());

        statistics.setCount(bigDecimalSummaryStatistics.getCount());

        Object max = bigDecimalSummaryStatistics.getMax();
        statistics.setMax(((BigDecimal) max).setScale(2,4).toString());

        Object min = bigDecimalSummaryStatistics.getMin();
        statistics.setMin(((BigDecimal) min).setScale(2,4).toString());

        Object sum = bigDecimalSummaryStatistics.getSum();
        statistics.setSum(((BigDecimal) sum).setScale(2,4).toString());

        LOGGER.info("Returning last sixty second statistics."+statistics);

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
