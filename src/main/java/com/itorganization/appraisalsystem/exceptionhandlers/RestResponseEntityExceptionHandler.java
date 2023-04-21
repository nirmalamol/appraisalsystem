package com.itorganization.appraisalsystem.exceptionhandlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ EmployeeAppraisalException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        String json = createJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<Object>(json, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static String createJson(int message, String reason) {
        return "{\"error\" : \"" + message + "\"," +
                "\"message\" : \"" + reason  + "\"}";
    }

}