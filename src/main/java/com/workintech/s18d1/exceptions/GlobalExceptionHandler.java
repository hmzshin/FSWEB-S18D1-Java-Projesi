package com.workintech.s18d1.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> burgerExceptionHandler(BurgerException burgerException) {
        log.error(burgerException.getMessage());

        ExceptionResponse exceptionResponse = new ExceptionResponse(burgerException.getHttpStatus(),
                burgerException.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, burgerException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> globalExceptionHandler(Exception exception) {
        log.error(exception.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
