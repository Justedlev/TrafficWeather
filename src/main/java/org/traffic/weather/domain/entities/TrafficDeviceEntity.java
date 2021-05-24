package org.traffic.weather.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAFFIC")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrafficDeviceEntity {

    @Id
    @Column(name = "object_id")
    private Long objectId;
    private String id;
    @Column(name = "last_heartbeat")
    private Long lastHeartbeat;
    @Column(name = "device_longitude")
    private Double deviceLongitude;
    @Column(name = "device_latitude")
    private Double deviceLatitude;
    @Column(name = "device_height")
    private Integer deviceHeight;
    private boolean enabled;
    private boolean connected;

}
