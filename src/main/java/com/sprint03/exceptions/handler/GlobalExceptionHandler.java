package com.sprint03.exceptions.handler;

import com.sprint03.exceptions.badrequest.BadRequestDetails;
import com.sprint03.exceptions.default_error.DefaultErrorDetails;
import com.sprint03.exceptions.method_not_allowed.MethodNotAllowedDetails;
import com.sprint03.exceptions.notfound.NotFoundDetails;
import com.sprint03.exceptions.notfound.NotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public NotFoundDetails notFound(NotFoundException notFoundException){
        return NotFoundDetails.builder()
                .error(notFoundException.getMessage())
                .status(NOT_FOUND.value())
                .timestamp(Instant.now())
                .message("Use a valid ID.")
                .build();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(BAD_REQUEST)
    public BadRequestDetails badRequest(WebExchangeBindException exchangeBindException){
//        Map<String, String> error = new HashMap<>();
//        List<FieldError> errorList = exchangeBindException.getBindingResult().getFieldErrors();
//        errorList.forEach(p -> error.put(p.getField(), p.getDefaultMessage()));

        return BadRequestDetails.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .fields(fieldsErrors(exchangeBindException))
                .developerMessage("Error! Check your request")
                .build();
    }

    @ExceptionHandler(Exception.class)
    public DefaultErrorDetails defaultError(Exception internalServerError){
        return DefaultErrorDetails.builder()
                .error(internalServerError.getMessage())
                .status(INTERNAL_SERVER_ERROR.value())
                .timestamp(Instant.now())
                .message("Internal server error.")
                .build();
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public MethodNotAllowedDetails methodNotAllowed(MethodNotAllowedException methodNotAllowedException){
        return MethodNotAllowedDetails.builder()
                .error(methodNotAllowedException.getMessage())
                .status(METHOD_NOT_ALLOWED.value())
                .timestamp(Instant.now())
                .message("Method not allowed.")
                .build();
    }

    private  List<ErrorObject> fieldsErrors(WebExchangeBindException exchangeBindException){
        return exchangeBindException.getBindingResult().getFieldErrors().stream()
                .map(errors -> new ErrorObject((errors.getDefaultMessage()),
                        errors.getField(),
                        errors.getRejectedValue()))
                .collect(Collectors.toList());
    }

}
