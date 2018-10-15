package com.n26.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.exception.FieldValidatorException;
import com.n26.service.TransactionService;
import com.n26.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    private MockMvc mockMvc;
    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        transactionService = Mockito.mock(TransactionService.class);

        Field field = ReflectionUtils.findField(TransactionController.class, "transactionService");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, transactionController, transactionService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testMakeTransaction() throws Exception {

        String makeTransactionJson = buildTransactionDtoAsJson("10.23", -20000);
        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(makeTransactionJson))
                .andExpect(status().is(201));

    }

    @Test
    public void deleteAllTransactions() throws Exception {

        Mockito.when(transactionService.deleteAllTransactions()).thenReturn(true);
        this.mockMvc.perform(delete("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(204));
    }

    public String buildTransactionDtoAsJson(String amount, long offset) throws JsonProcessingException {

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(amount);
        Instant timestamp = Instant.now().plusMillis(offset);
        transactionDto.setTimestamp(DateTimeFormatter.ISO_INSTANT.format(timestamp));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(transactionDto);
    }
}