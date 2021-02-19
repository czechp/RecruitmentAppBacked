package com.github.czechp.recruitmentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice()
class ControllerAdviceComponent {
    @ExceptionHandler({EntityNotFoundException.class})
    ResponseEntity entityNotFoundExceptionHandler(Exception exception) {
        return buildExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity buildExceptionResponse(String message, HttpStatus httpStatus) {
        HashMap<String, String> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now().toString());
        responseBody.put("message", message);

        return new ResponseEntity(responseBody, httpStatus);
    }
}
