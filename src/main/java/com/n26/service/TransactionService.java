package com.n26.service;

import com.n26.entity.Transaction;
import org.springframework.stereotype.Service;

/**
 * @author rohitkumar
 */
@Service
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
