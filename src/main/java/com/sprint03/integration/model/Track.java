package com.sprint03.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Track {

    @JsonProperty("key")
    private String key;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;

}