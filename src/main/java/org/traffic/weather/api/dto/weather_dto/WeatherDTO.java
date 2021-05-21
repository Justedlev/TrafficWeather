package org.traffic.weather.api.dto.weather_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

    private List<CloudsDTO> weather;
    private MainWeatherDTO main;
    private Integer visibility;
    private WindDTO wind;
    private String name;

}
