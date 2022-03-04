package com.sprint03.exceptions.badrequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends HttpClientErrorException {
    public BadRequestException(HttpStatus status) {
        super(status);
    }
}


