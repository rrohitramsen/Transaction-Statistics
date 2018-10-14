package com.n26.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ApiOperation(value = "Returns the statistic based of the transactions of the last 60 seconds.", response = Response.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Response> getStatistics() {

        LOGGER.debug("Inside get statistics API.");
        /*ResponseBody responseBody = entityService.createEntity(entity);
        String message = "Entity created successfully.";
        Response<ResponseBody>  apiResponse = new Response(HttpStatus.CREATED.toString(), responseBody);
        LOGGER.debug(message);*/
        return null;//new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
