package com.chan.EmployeeService.Exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.chan.EmployeeService.Exceptions.ResourcenotfoundException;
import com.chan.EmployeeService.DataTransferObject.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourcenotfoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleResourceNotFound(ResourcenotfoundException ex) {
    return new ErrorResponse(404, ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

  public ErrorResponse handleGenericException(Exception ex) {
    return new ErrorResponse(500, ex.getMessage(), LocalDateTime.now());

  }

}
