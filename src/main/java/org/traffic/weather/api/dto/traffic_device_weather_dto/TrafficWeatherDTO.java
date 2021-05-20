package org.traffic.weather.api.dto.traffic_device_weather_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TrafficWeatherDTO {

    private String trafficId;
    private TrafficCoordDTO coord;
    private WeatherDTO weather;

}
