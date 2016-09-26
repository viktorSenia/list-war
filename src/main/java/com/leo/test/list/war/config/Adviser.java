package com.leo.test.list.war.config;

import com.leo.test.list.war.model.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Adviser {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleNotFound(BadRequestException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

}
