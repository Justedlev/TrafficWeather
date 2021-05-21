package org.traffic.weather.api.dto.weather_dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class WindDTO {

    private Double speed;
    private Integer deg;

}
