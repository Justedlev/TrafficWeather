package org.traffic.weather.api.dto.traffic_device_weather_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TrafficCoordDTO {

    private Double longitude;
    private Double latitude;
    private Integer deviceHeight;

}
