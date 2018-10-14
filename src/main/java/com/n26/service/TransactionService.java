package com.n26.service;

import com.n26.entity.Transaction;

/**
 * @author rohitkumar
 */
public interface TransactionService {

    /**
     * Complete the transaction.
     * @param transaction
     */
    void makeTransaction(Transaction transaction);

    /**
     * This method deletes all transactions.
     * @return true or false
     */
    boolean deleteAllTransactions();

}
