package com.n26.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rohitkumar
 *
 */
@RestController
@RequestMapping("/transactions")
@Api(value="RESTful API for transactions. The main use case for the API is to make and delete transactions.")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);


    @ApiOperation(value = "Called every time a transaction is made. It is also the sole input of this rest API.", response = Response.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Response> makeTransaction(@RequestBody Request<com.n26.api.RequestBody> request) {

        LOGGER.debug("Inside make transaction API.");
        /*ResponseBody responseBody = entityService.createEntity(entity);
        String message = "Entity created successfully.";
        Response<ResponseBody>  apiResponse = new Response(HttpStatus.CREATED.toString(), responseBody);
        LOGGER.debug(message);*/
        return null;//new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Deletes all transactions..", response = Response.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Response> deleteAllTransactions() {

        LOGGER.debug("Inside delete all transactions API.");
        /*ResponseBody responseBody = entityService.createEntity(entity);
        String message = "Entity created successfully.";
        Response<ResponseBody>  apiResponse = new Response(HttpStatus.CREATED.toString(), responseBody);
        LOGGER.debug(message);*/
        return null;//new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

}
