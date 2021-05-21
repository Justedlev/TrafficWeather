package org.traffic.weather.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Components {

    @Bean
    ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Bean
    RestTemplate getRestTemplateBean() {
        return new RestTemplate();
    }

}
