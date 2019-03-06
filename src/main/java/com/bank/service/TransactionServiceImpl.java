package com.bank.service;

import com.bank.entity.Transaction;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
