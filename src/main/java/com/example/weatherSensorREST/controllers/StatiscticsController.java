package com.example.weatherSensorREST.controllers;

import com.example.weatherSensorREST.dto.StatDTO;
import com.example.weatherSensorREST.mapppers.StatsMapper;
import com.example.weatherSensorREST.services.StatService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatiscticsController {

  private final StatService statService;
  private final StatsMapper statsMapper;

  @Autowired
  public StatiscticsController(StatService statService, StatsMapper statsMapper) {
    this.statService = statService;
    this.statsMapper = statsMapper;
  }

  @GetMapping()
  public List<StatDTO> showStatistics() {
    return statService.showStatistics().stream().map(stat -> statsMapper.convertToStatDTO(stat))
        .collect(Collectors.toList());
  }


}
