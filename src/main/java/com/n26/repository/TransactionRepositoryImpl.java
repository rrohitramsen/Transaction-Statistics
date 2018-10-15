package com.n26.repository;

import com.n26.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private LRUCache lruCache;

    @Override
    public void makeTransaction(Transaction transaction) {
        lruCache.add(transaction, transaction.getTimestamp().getTime());
    }

    @Override
    public boolean deleteAllTransactions() {
        return lruCache.deleteAllEntries();
    }

    @Override
    public Map<Transaction, Long> getAllTransactions() {
        return lruCache.getAllEntriesSortedByValue();
    }
}
