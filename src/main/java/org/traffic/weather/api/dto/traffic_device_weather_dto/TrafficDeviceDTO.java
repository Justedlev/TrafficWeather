package org.traffic.weather.api.dto.traffic_device_weather_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TrafficDeviceDTO {

    private String id;
    private Long lastHeartbeat;
    private Double deviceLongitude;
    private Double deviceLatitude;
    private Integer deviceHeight;
    private boolean enabled;
    private boolean connected;

}
