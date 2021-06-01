package org.traffic.weather.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.traffic.weather.domain.entities.TrafficDeviceEntity;

import java.util.Optional;

public interface TrafficWeatherRepository extends JpaRepository<TrafficDeviceEntity, Long> {

    @Query(value = "SELECT * FROM traffic WHERE id=:id", nativeQuery = true)
    Optional<TrafficDeviceEntity> findById(@Param("id") String id);

}
