package org.traffic.weather.service.interfaces;

import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficDeviceDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficWeatherDTO;

import java.util.List;
import java.util.Optional;

public interface ITrafficWeather {

    List<TrafficDeviceDTO> getAllTrafficDevices();
    Optional<TrafficWeatherDTO> canBeRepaired(String id);

}
