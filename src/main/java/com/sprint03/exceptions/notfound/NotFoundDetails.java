package com.sprint03.exceptions.notfound;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@SuperBuilder
@Data
public class NotFoundDetails {

    private Integer status;
    private Instant timestamp;
    private String error;
    private String message;

}
