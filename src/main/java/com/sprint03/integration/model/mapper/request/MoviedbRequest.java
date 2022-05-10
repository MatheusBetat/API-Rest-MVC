package com.sprint03.integration.model.mapper.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MoviedbRequest {

    @JsonProperty("t")
    private String title;
}
