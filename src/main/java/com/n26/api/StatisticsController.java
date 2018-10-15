package com.n26.api;

import com.n26.entity.Statistics;
import com.n26.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rohitkumar
 *
 */
@RestController
@RequestMapping("/statistics")
@Api(value="RESTful API for statistics. The main use case for the API is to calculate realtime statistics for the last 60 seconds of transactions.")
public class StatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value = "Returns the statistic based of the transactions of the last 60 seconds.", response = Statistics.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = Statistics.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Statistics> getStatistics() {

        LOGGER.debug("Inside get statistics API.");
        Statistics statistics = statisticsService.getLastSixtySecondsStatistics();
        return new ResponseEntity<Statistics>(statistics, HttpStatus.OK);
    }
}
