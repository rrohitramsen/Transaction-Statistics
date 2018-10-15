package com.n26.repository;

import com.n26.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author rohitkumar
 *
 * LRU Cache for storing transactions and removing all older transactions which are older then 60 seconds..
 */
@Component
public class LRUCache extends LinkedHashMap<Transaction, Long> {

    public static final int EVICTION_TIME_IN_MILLIS = 60000;

    @Override
    protected boolean removeEldestEntry(Map.Entry<Transaction, Long> eldest) {


        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();
        Long entryTime = eldest.getValue();

        return (currentTime - entryTime > EVICTION_TIME_IN_MILLIS);

    }

    /**
     * Set the element with input key and value in the cache. If the element
     * already exits, it updates its value.
     *
     * @param key
     *            Key of the element
     * @param value
     *            Value of the element
     */
    public synchronized void add(Transaction key, Long value) {
        super.put(key, value);
    }

    /**
     * Return all keys of the cache
     * @return Map
     *      Map of entries sorted by value in reverse order.
     */
    public synchronized Map<Transaction, Long> getAllEntriesSortedByValue() {

        Map<Transaction, Long> sortedByValue = new HashMap<>();
        this.entrySet().stream()
                .sorted(Map.Entry.<Transaction, Long>comparingByValue().reversed())
                .forEachOrdered(x -> sortedByValue.put(x.getKey(), x.getValue()));

        return sortedByValue;

    }

    /**
     *This method deletes all entries from the cache;
     * The cache will be empty after this call.
     */
    public synchronized boolean deleteAllEntries() {
        super.clear();
        return super.isEmpty();
    }


}
