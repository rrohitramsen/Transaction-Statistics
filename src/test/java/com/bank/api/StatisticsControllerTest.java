package com.bank.api;

import com.bank.entity.Statistics;
import com.bank.service.StatisticsService;
import com.bank.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
public class StatisticsControllerTest {

    @InjectMocks
    private StatisticsController statisticsController;

    private MockMvc mockMvc;
    private StatisticsService statisticsService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        statisticsService = Mockito.mock(StatisticsService.class);

        Field field = ReflectionUtils.findField(StatisticsController.class, "statisticsService");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, statisticsController, statisticsService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }


    @Test
    public void testStatistics() throws Exception {

        Statistics statistics = FileUtils.readObjectFromJsonFile(Statistics.class, "statistics.json");
        Mockito.when(statisticsService.getLastSixtySecondsStatistics()).thenReturn(statistics);

        ResponseEntity<Statistics> expectedResponseEntity = new ResponseEntity<>(statistics, HttpStatus.OK);
        this.mockMvc.perform(get("/statistics"))
                .andExpect(mvcResult -> equals(expectedResponseEntity));

    }
}