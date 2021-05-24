package org.traffic.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.traffic.weather.api.ApiConstants;
import org.traffic.weather.api.CodeWithReturnedData;
import org.traffic.weather.api.Codes;
import org.traffic.weather.api.dto.DescriptionDTO;
import org.traffic.weather.service.TrafficWeatherService;

@Controller
@RequestMapping(ApiConstants.MAIN_ENDPOINT)
public class TrafficWeatherController {

    @Autowired
    TrafficWeatherService service;

    @GetMapping
    ResponseEntity<?> main(@RequestParam(required = false) String trafficId) {
        if(trafficId == null || trafficId.isEmpty()) {
            return ResponseEntity.ok(service.getAllTrafficDevices());
        }
        CodeWithReturnedData<?> cwrd = service.canBeRepaired(trafficId);
        if(cwrd.getCode() == Codes.TRAFFIC_DEVICE_NOT_FOUND) {
            DescriptionDTO description =
                    new DescriptionDTO(String.format("Traffic device by id '%s' not found!", trafficId));
            return new ResponseEntity<>(description, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cwrd.getReturnedData());
    }

}
