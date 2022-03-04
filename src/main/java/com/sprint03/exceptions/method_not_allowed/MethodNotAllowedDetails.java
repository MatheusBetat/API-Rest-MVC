package com.sprint03.exceptions.method_not_allowed;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class MethodNotAllowedDetails {

    private Integer status;
    private Instant timestamp;
    private String error;
    private String message;
}
