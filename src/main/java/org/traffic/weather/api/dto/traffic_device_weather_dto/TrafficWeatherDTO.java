package org.traffic.weather.api.dto.traffic_device_weather_dto;

import lombok.*;
import org.traffic.weather.api.dto.weather_dto.WeatherDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrafficWeatherDTO {

    private TrafficDeviceDTO trafficDevice;
    private WeatherDTO weather;

}
