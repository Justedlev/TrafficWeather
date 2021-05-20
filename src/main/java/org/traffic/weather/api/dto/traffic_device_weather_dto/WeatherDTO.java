package org.traffic.weather.api.dto.traffic_device_weather_dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class WeatherDTO {

    private String city;
    private Double temp;
    private Double feelsLike;
    private Double tempMin;
    private Double tempMax;
    private Integer pressure;
    private Integer humidity;
    private Integer visibility;
    private Double windSpeed;

}
