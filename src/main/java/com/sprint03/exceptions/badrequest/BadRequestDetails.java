package com.sprint03.exceptions.badrequest;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Map;

@Data
@SuperBuilder
public class BadRequestDetails {

    private Integer status;
    private String title;
    private Instant timestamp;
    private Map<String, String> details;
    private String developerMessage;

}
