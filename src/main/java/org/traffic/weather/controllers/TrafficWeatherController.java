package org.traffic.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.traffic.weather.api.ApiConstants;
import org.traffic.weather.api.dto.DescriptionDTO;
import org.traffic.weather.service.interfaces.ITrafficWeather;

import java.util.Optional;

@Controller
@RequestMapping(ApiConstants.MAIN_ENDPOINT)
public class TrafficWeatherController {

    @Autowired
    @Qualifier("main-service")
    private ITrafficWeather service;

    @GetMapping
    ResponseEntity<?> main(@RequestParam(required = false) String trafficId) {
        if(trafficId == null || trafficId.isEmpty()) {
            return ResponseEntity.ok(service.getAllTrafficDevices());
        }
        Optional<?> data = service.canBeRepaired(trafficId);
        DescriptionDTO description =
                new DescriptionDTO(String.format("Traffic device by id '%s' not found!", trafficId));
        return data.isPresent() ? ResponseEntity.ok(data.get()) :
                new ResponseEntity<>(description, HttpStatus.NOT_FOUND);
    }

}
