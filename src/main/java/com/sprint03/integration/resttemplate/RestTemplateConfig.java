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
                .defaultHeader("x-rapidapi-host", "moviesdb5.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", "c313495212msh61074658381b602p118932jsn79f0a21a8167")
                .build();

    }
}