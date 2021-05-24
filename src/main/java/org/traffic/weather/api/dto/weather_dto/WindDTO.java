package org.traffic.weather.api.dto.weather_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindDTO {

    private Double speed;
    private Integer deg;
    private Double gust;

}
