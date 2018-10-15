package com.n26.service;

import com.n26.entity.Statistics;
import org.springframework.stereotype.Service;

/**
 * @author rohitkumar
 */
@Service
public interface StatisticsService {

    /**
     * This method returns the last 60 seconds statistics.
     * @return
     */
    Statistics getLastSixtySecondsStatistics();
}
