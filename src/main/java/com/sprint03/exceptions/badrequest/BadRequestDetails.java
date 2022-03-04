package com.sprint03.exceptions.badrequest;

import com.sprint03.exceptions.handler.ErrorObject;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class BadRequestDetails {

    private String message;
    private LocalDateTime timestamp;
    private List<ErrorObject> fields;
    private String developerMessage;

}
