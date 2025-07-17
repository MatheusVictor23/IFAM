package com.example.demo.handler;

import com.example.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private ResponseEntity<RestErrorMessage> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new RestErrorMessage(status.value(), message));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestErrorMessage> handleBadRequestException(BadRequestException e) {
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleNotFoundException(NotFoundException e) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
