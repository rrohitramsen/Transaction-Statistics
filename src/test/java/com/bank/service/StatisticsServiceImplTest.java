package com.bank.service;

import com.bank.entity.Statistics;
import com.bank.entity.Transaction;
import com.bank.repository.TransactionRepository;
import com.bank.utils.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class StatisticsServiceImplTest {


    private StatisticsServiceImpl statisticsService;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() throws Exception {

        transactionRepository = Mockito.mock(TransactionRepository.class);
        statisticsService = Mockito.mock(StatisticsServiceImpl.class);

        Field field = ReflectionUtils.findField(StatisticsServiceImpl.class, "transactionRepository");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, statisticsService, transactionRepository);

    }

    @Test
    public void testGetLastSixtySecondsStatistics() throws IOException {

        Map<Transaction, Long> allTransactions = getAllTransactions();

        Mockito.when(transactionRepository.getAllTransactions()).thenReturn(allTransactions);
        Mockito.when(statisticsService.getLastSixtySecondsStatistics()).thenCallRealMethod();

        Statistics actualResult = statisticsService.getLastSixtySecondsStatistics();
        Statistics expectedResult = FileUtils.readObjectFromJsonFile(Statistics.class, "expected_statistics.json");;

        Assert.assertEquals(expectedResult.getAvg(), actualResult.getAvg());
        Assert.assertEquals(expectedResult.getCount(), actualResult.getCount());
        Assert.assertEquals(expectedResult.getMax(), actualResult.getMax());
        Assert.assertEquals(expectedResult.getMin(), actualResult.getMin());
        Assert.assertEquals(expectedResult.getSum(), actualResult.getSum());

    }

    @Test
    public void testGetLastSixtySecondsStatisticsWhenNoTransactionsAvailable() throws IOException {

        Mockito.when(transactionRepository.getAllTransactions()).thenReturn(null);
        Mockito.when(statisticsService.getLastSixtySecondsStatistics()).thenCallRealMethod();

        Statistics actualResult = statisticsService.getLastSixtySecondsStatistics();
        Statistics expectedResult = FileUtils.readObjectFromJsonFile(Statistics.class, "empty_statistics.json");

        Assert.assertEquals(expectedResult.getAvg(), actualResult.getAvg());
        Assert.assertEquals(expectedResult.getCount(), actualResult.getCount());
        Assert.assertEquals(expectedResult.getMax(), actualResult.getMax());
        Assert.assertEquals(expectedResult.getMin(), actualResult.getMin());
        Assert.assertEquals(expectedResult.getSum(), actualResult.getSum());

    }


    private Map<Transaction, Long> getAllTransactions() {

        Map<Transaction, Long> allTransactions = new HashMap<>();
        allTransactions.put(buildTransactionDtoAsJson(new BigDecimal(10.24), -20000), 20000L);
        allTransactions.put(buildTransactionDtoAsJson(new BigDecimal(20.24), -21000), 20000L);
        allTransactions.put(buildTransactionDtoAsJson(new BigDecimal(30.24), -22000), 20000L);
        allTransactions.put(buildTransactionDtoAsJson(new BigDecimal(40.24), -23000), 20000L);
        return allTransactions;

    }

    public Transaction buildTransactionDtoAsJson(BigDecimal amount, long offset){

        Transaction transaction = new Transaction();
        Instant timestamp = Instant.now().plusMillis(offset);
        transaction.setAmount(amount);
        Date currentDate = new Date(timestamp.toEpochMilli());
        transaction.setTimestamp(currentDate);
        return transaction;
    }

}