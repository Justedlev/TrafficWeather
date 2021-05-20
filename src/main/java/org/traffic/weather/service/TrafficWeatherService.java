package org.traffic.weather.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.traffic.weather.api.ApiConstants;
import org.traffic.weather.api.CodeWithReturnedData;
import org.traffic.weather.api.Codes;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficCoordDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficDeviceDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.TrafficWeatherDTO;
import org.traffic.weather.api.dto.traffic_device_weather_dto.WeatherDTO;
import org.traffic.weather.domain.dao.TrafficWeatherRepository;
import org.traffic.weather.domain.entities.TrafficDeviceEntity;
import org.traffic.weather.service.interfaces.ITrafficWeather;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrafficWeatherService implements ITrafficWeather {

    @Autowired
    TrafficWeatherRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<TrafficDeviceDTO> getAllTrafficDevices() {
        List<TrafficDeviceDTO> devices = repository.findAll().stream()
                .map(this::convertTrafficDeviceEntityToTrafficDeviceDTO)
                .collect(Collectors.toList());
        log.debug("getting all data from db, data = {}" , devices);
        return devices;
    }

    @Override
    public CodeWithReturnedData<TrafficWeatherDTO> canBeRepaired(String id) {
        TrafficDeviceEntity trafficDevice = repository.findById(id);
        if (trafficDevice == null) {
            log.debug("no data received from db");
            return new CodeWithReturnedData<>(Codes.TRAFFIC_DEVICE_NOT_FOUND, null);
        }
        log.debug("getting data from db, data = {}", trafficDevice);
        return getTrafficWeather(trafficDevice);
    }

    private CodeWithReturnedData<TrafficWeatherDTO> getTrafficWeather(TrafficDeviceEntity trafficDevice) {
        URI api = getWeatherApiURI(trafficDevice.getDeviceLongitude(), trafficDevice.getDeviceLatitude());
        ResponseEntity<String> response = restTemplate.getForEntity(api, String.class);
        JSONObject weatherData = new JSONObject(response.getBody());
        log.debug("getting data from weather api = {}, data = {}", api, weatherData);
        TrafficWeatherDTO dto = getTrafficWeatherDTO(weatherData, trafficDevice);
        return new CodeWithReturnedData<>(Codes.OK, dto);
    }

    private TrafficWeatherDTO getTrafficWeatherDTO(JSONObject weatherData, TrafficDeviceEntity trafficDevice) {
        TrafficCoordDTO coord = new TrafficCoordDTO(trafficDevice.getDeviceLongitude(),
                trafficDevice.getDeviceLatitude(), trafficDevice.getDeviceHeight());
        JSONObject main = weatherData.getJSONObject("main");
        String city = weatherData.getString("name");
        Double temp = main.getDouble("temp");
        Double feelsLike = main.getDouble("feels_like");
        Double tempMin = main.getDouble("temp_min");
        Double tempMax = main.getDouble("temp_max");
        Integer pressure = main.getInt("pressure");
        Integer humidity = main.getInt("humidity");
        Integer visibility = weatherData.getInt("visibility");
        Double windSpeed = weatherData.getJSONObject("wind").getDouble("speed");
        WeatherDTO weather = new WeatherDTO(city, temp, feelsLike, tempMin, tempMax, pressure, humidity, visibility, windSpeed);
        return new TrafficWeatherDTO(trafficDevice.getId(), coord, weather);
    }

    private URI getWeatherApiURI(double longitude, double latitude) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.WEATHER_API_URL)
                .path(ApiConstants.WEATHER_API_PATH)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", ApiConstants.WEATHER_API_KEY)
                .queryParam("units","metric")
                .build().toUri();
    }

    private TrafficDeviceDTO convertTrafficDeviceEntityToTrafficDeviceDTO(TrafficDeviceEntity entity) {
        return new TrafficDeviceDTO(entity.getId(),
                entity.getLastHeartbeat(),
                entity.getDeviceLongitude(),
                entity.getDeviceLatitude(),
                entity.getDeviceHeight(),
                entity.isEnabled(),
                entity.isConnected());
    }

}
