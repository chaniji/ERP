package com.Chan.InventoryService.Exceptions;

import com.Chan.InventoryService.DataTransferObjects.ErrorResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse ResourceHandler(ResourceNotFoundException e) {
        return new ErrorResponse(404, e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse ExceptionHandler(Exception e) {
        return new ErrorResponse(
            404,
            "INTERNAL SERVER ERROR",
            LocalDateTime.now()
        );
    }
}
