package com.bank.api;

import com.bank.entity.Transaction;
import com.bank.helper.DateUtils;
import com.bank.service.TransactionService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author rohitkumar
 *
 */
@RestController
@RequestMapping("/transactions")
@Api(value="RESTful API for transactions. The main use case for the API is to make and delete transactions.")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);


    @Autowired
    private TransactionService transactionService;


    @ApiOperation(value = "Called every time a transaction is made. It is also the sole input of this rest API.", response = ResponseEntity.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 204, message = "Transaction is older than 60 seconds"),
            @ApiResponse(code = 422, message = "If any of the fields are not parsable or the transaction date is in the future"),
            @ApiResponse(code = 400, message = "Invalid Json"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Object> makeTransaction(@Valid @RequestBody TransactionDto transactionDto) {

        LOGGER.debug("Inside make transaction API."+transactionDto);

        Transaction transaction = buildTransaction(transactionDto);

        transactionService.makeTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }


    @ApiOperation(value = "Deletes all existing transactions.")
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "All transactions are deleted."),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Object> deleteAllTransactions() {

        LOGGER.debug("Inside delete all transactions API.");
        boolean deleted = transactionService.deleteAllTransactions();

        ResponseEntity responseEntity;
        if (deleted) {
           responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
           LOGGER.info("All transactions deleted successfully.");
        }else {
           responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            LOGGER.info("Transactions not deleted.");
        }
        return responseEntity;
    }

    private Transaction buildTransaction(TransactionDto transactionDto) {

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
        transaction.setTimestamp(DateUtils.parseDate(transactionDto.getTimestamp()));

        return transaction;
    }

}
