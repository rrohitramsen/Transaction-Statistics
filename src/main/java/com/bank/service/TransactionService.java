package com.bank.service;

import com.bank.entity.Transaction;
import org.springframework.stereotype.Service;

/**
 * @author rohitkumar
 */
@Service
public interface TransactionService {

    /**
     * Make the transaction.
     * @param transaction
     */
    void makeTransaction(Transaction transaction);

    /**
     * This method deletes all transactions.
     * @return true or false
     */
    boolean deleteAllTransactions();

}
