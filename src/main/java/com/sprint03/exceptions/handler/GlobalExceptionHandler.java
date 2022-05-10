package com.sprint03.exceptions.handler;


import com.sprint03.exceptions.notfound.NotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException error) {
        return (ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(NOT_FOUND.name())
                        .field(error.getMessage())
                        .build()))
                .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception error) {
        return (ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(INTERNAL_SERVER_ERROR.name())
                        .field(error.getMessage())
                        .build()))
                .build());

    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse methodNotAllowed(MethodNotAllowedException error) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(INTERNAL_SERVER_ERROR.name())
                        .field(error.getMessage())
                        .build()))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public ErrorResponse handlerBadRequestException(MethodArgumentNotValidException error) {
        List<FieldError> errorList = error.getBindingResult().getFieldErrors();
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(errorList.stream().map(fieldError -> ErrorObject.builder()
                        .message(fieldError.getDefaultMessage())
                        .field(fieldError.getField()).build()).collect(toList()))
                .build();

    }
}
