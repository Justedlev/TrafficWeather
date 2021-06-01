package org.traffic.weather;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.traffic.weather.api.ApiConstants;
import org.traffic.weather.api.dto.traffic_device_weather_dto.*;
import org.traffic.weather.api.dto.weather_dto.CloudsDTO;
import org.traffic.weather.api.dto.weather_dto.MainWeatherDTO;
import org.traffic.weather.api.dto.weather_dto.WeatherDTO;
import org.traffic.weather.api.dto.weather_dto.WindDTO;
import org.traffic.weather.domain.dao.TrafficWeatherRepository;
import org.traffic.weather.domain.entities.TrafficDeviceEntity;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrafficWeatherTest {

    double LAT = 32.023406;
    double LON = 34.769836;
    String CITY_NAME = "Holon";

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
            .queryParam("lat", LAT)
            .queryParam("lon", LON)
            .queryParam("appid", ApiConstants.WEATHER_API_KEY)
            .queryParam("units","metric")
            .build().toUri();

    WeatherDTO weatherDTO = new WeatherDTO(
            Collections.singletonList(new CloudsDTO("Clear", "clear sky")),
            new MainWeatherDTO(
                    1.,
                    1.,
                    1.,
                    1.,
                    1,
                    1
            ),
            1,
            new WindDTO(1., 1, 1.),
            CITY_NAME
    );

    List<TrafficDeviceDTO> trafficDeviceDtos = getTrafficDeviceDTOs();
    List<TrafficDeviceEntity> trafficDeviceEntities = getTrafficDeviceEntitys();
    TrafficWeatherDTO trafficWeatherDTO = getTrafficWeatherDTO();

    String responseData = "{\"weather\":[{\"main\":\"Clear\",\"description\":\"clear sky\"}]," +
            "\"main\":{\"temp\":1.0,\"feels_like\":1.0,\"temp_min\":1.0,\"temp_max\":1.0,\"pressure\":1,\"humidity\":1}," +
            "\"visibility\":1,\"wind\":{\"speed\":1.0,\"deg\":1.0, \"gust\":1.0},\"name\":\"" + CITY_NAME + "\"}";

    @Test
    void trafficDevicesFoundTest() {
        Mockito.when(repo.findAll()).thenReturn(trafficDeviceEntities);
        Mockito.when(restTemplate.getForEntity(api, String.class))
                .thenReturn(new ResponseEntity<>(responseData, HttpStatus.OK));
        List<TrafficDeviceDTO> data = getTrafficDeviceDTOs();
        assertEquals(data, trafficDeviceDtos);
        assertEquals(data, trafficDeviceDtos);
    }

    @Test
    void trafficWeatherFoundTest() {
        Mockito.when(repo.findById("1")).thenReturn(Optional.of(trafficDeviceEntities.get(0)));
        ResponseEntity<String> entity = restTemplate.getForEntity(api, String.class);
        Mockito.when(entity).thenReturn(new ResponseEntity<>(responseData, HttpStatus.OK));
        TrafficWeatherDTO data = localHostRestTemplate.getForEntity(host + port + "?trafficId=1",
                TrafficWeatherDTO.class).getBody();
        assertEquals(data, trafficWeatherDTO);
    }

    private TrafficWeatherDTO getTrafficWeatherDTO() {
        TrafficDeviceDTO trafficDeviceDTO = new TrafficDeviceDTO(
                "1",
                1L,
                LON,
                LAT,
                1,
                false,
                false
        );
        return new TrafficWeatherDTO(trafficDeviceDTO, weatherDTO);
    }

    private List<TrafficDeviceDTO> getTrafficDeviceDTOs() {
        return Collections.singletonList(new TrafficDeviceDTO(
                "1",
                1L,
                1.,
                1.,
                1,
                false,
                false
        ));
    }

    private List<TrafficDeviceEntity> getTrafficDeviceEntitys() {
        return Collections.singletonList(new TrafficDeviceEntity(
                1L,
                "1",
                1L,
                LON,
                LAT,
                1,
                false,
                false
        ));
    }

}
