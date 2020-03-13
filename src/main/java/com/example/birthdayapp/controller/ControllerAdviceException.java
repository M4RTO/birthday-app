package com.example.birthdayapp.controller;

import com.example.birthdayapp.exception.NotFoundAnimatorException;
import com.example.birthdayapp.exception.NotFoundGuestException;
import com.example.birthdayapp.exception.NotFoundRoomException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceException  extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundRoomException.class)
    public ResponseEntity notFoundRoomException(NotFoundRoomException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundGuestException.class)
    public ResponseEntity notFoundGuestException(NotFoundGuestException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundAnimatorException.class)
    public ResponseEntity notFoundAnimatorException(NotFoundAnimatorException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }


}
