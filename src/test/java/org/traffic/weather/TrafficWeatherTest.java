package org.traffic.weather;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.traffic.weather.api.ApiConstants;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficCoordDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficWeatherDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.WeatherDTO;
import org.traffic.weather.domain.dao.TrafficWeatherRepository;
import org.traffic.weather.domain.entities.TrafficDeviceEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrafficWeatherTest {

    @LocalServerPort
    int port;

    String host = "http://localhost:";

    @MockBean
    TrafficWeatherRepository repo;

    @MockBean
    RestTemplate restTemplate;

    RestTemplate localHostRestTemplate = new RestTemplate();

    URI api = UriComponentsBuilder.fromHttpUrl(ApiConstants.WEATHER_API_URL)
            .path(ApiConstants.WEATHER_API_PATH)
            .queryParam("lat", 1.)
            .queryParam("lon", 1.)
            .queryParam("appid", ApiConstants.WEATHER_API_KEY)
            .queryParam("units","metric")
            .build().toUri();

    String responseData = "{\"main\":{" +
            "\"temp\":1.0," +
            "\"feels_like\":1.0," +
            "\"temp_min\":1.0," +
            "\"temp_max\":1.0," +
            "\"pressure\":1," +
            "\"humidity\":1}," +
            "\"visibility\":1," +
            "\"wind\":{\"speed\":1.0},\"name\":\"city\"}";

    TrafficDeviceEntity entity = new TrafficDeviceEntity(
            1L,
            "1",
            1L,
            1.,
            1.,
            1,
            false,
            false
    );
    TrafficWeatherDTO dto = new TrafficWeatherDTO(
            "1",
            new TrafficCoordDTO(1., 1., 1),
            new WeatherDTO("city", 1., 1., 1., 1., 1, 1, 1, 1.)
    );

    @Test
    void trafficWeatherTest() {
        Mockito.when(repo.findById("1")).thenReturn(entity);
        ResponseEntity<String> entity = restTemplate.getForEntity(api, String.class);
        Mockito.when(entity).thenReturn(new ResponseEntity<>(responseData, HttpStatus.OK));
        assertEquals(localHostRestTemplate.getForEntity(host + port + ApiConstants.REPAIRED_ENDPOINT + "?trafficId=1",
                TrafficWeatherDTO.class).getBody(), dto);
        assertNull(localHostRestTemplate.getForEntity(host + port + ApiConstants.REPAIRED_ENDPOINT + "?trafficId=0",
                TrafficWeatherDTO.class).getBody());
    }

}
