package com.example.Company.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler({ResponseException.class})
    public ResponseEntity<Object> handleAll(final ResponseException e){
        return new ResponseEntity<>(e.getResponseBody(),e.getResponseBody().getHttpStatus());
    }
}
