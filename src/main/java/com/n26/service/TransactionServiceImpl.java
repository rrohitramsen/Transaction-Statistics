package com.n26.service;

import com.n26.entity.Transaction;
import com.n26.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void makeTransaction(Transaction transaction) {
        transactionRepository.makeTransaction(transaction);
    }

    @Override
    public boolean deleteAllTransactions() {
        return transactionRepository.deleteAllTransactions();
    }
}
