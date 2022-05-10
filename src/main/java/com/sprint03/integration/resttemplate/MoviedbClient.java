package com.sprint03.integration.resttemplate;

import com.sprint03.integration.model.mapper.request.MoviedbRequest;
import com.sprint03.integration.model.mapper.response.MoviedbResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MoviedbClient {

    private final RestTemplate restTemplate;

    public MoviedbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MoviedbResponse call(MoviedbRequest moviedbRequest){
        return this.restTemplate.getForObject("/om?t=" + moviedbRequest.getTitle(), MoviedbResponse.class);

    }
}
