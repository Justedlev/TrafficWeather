package org.traffic.weather.service.interfaces;

import org.traffic.weather.api.CodeWithReturnedData;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficDeviceDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficWeatherDTO;

import java.util.List;

public interface ITrafficWeather {
    List<TrafficDeviceDTO> getAllTrafficDevices();
    CodeWithReturnedData<TrafficWeatherDTO> canBeRepaired(String id);
}
