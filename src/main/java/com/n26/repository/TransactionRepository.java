package com.n26.repository;

import com.n26.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author rohitkumar
 */
@Repository
public interface TransactionRepository {

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

    /**
     * This method returns all transactions.
     * @return
     */
    Map<Transaction, Long> getAllTransactions();

}
