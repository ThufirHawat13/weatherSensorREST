package com.example.weatherSensorREST.controllers;

import com.example.weatherSensorREST.entities.Stat;
import com.example.weatherSensorREST.mapppers.StatsMapper;
import com.example.weatherSensorREST.services.StatService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.w3c.dom.stylesheets.LinkStyle;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatisticsController.class)
class StatisticsControllerTest {

  private final MockMvc mockMvc;

  @Autowired
  StatisticsControllerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @MockBean
  private StatService statService;

  @MockBean
  private StatsMapper statsMapper;


  @Test
  void showStatistics() throws Exception {
    List<Stat> statList = new ArrayList<>();
    Stat stat0 = Mockito.mock(Stat.class);
    Stat stat1 = Mockito.mock(Stat.class);
    statList.add(stat0);
    statList.add(stat1);
    Mockito.when(statService.showStatistics()).thenReturn(statList);

    mockMvc.perform(
            MockMvcRequestBuilders.get("/statistics"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(statService, Mockito.times(1))
        .showStatistics();
    Mockito.verify(statsMapper, Mockito.times(2))
        .convertToStatDTO(Mockito.any());
  }
}