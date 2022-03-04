package com.sprint03.exceptions.default_error;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class DefaultErrorDetails {

    private Integer status;
    private Instant timestamp;
    private String error;
    private String message;
}
