package com.sprint03.integration.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri("https://shazam.p.rapidapi.com")
                .defaultHeader("x-rapidapi-host", "shazam.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", "7ec877b37amsh766a40e8efcafa8p1d5a8djsne7690bf9bf0d")
                .build();

    }

}