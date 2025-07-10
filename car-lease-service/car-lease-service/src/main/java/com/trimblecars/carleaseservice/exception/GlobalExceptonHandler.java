package com.trimblecars.carleaseservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptonHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<?> handleResponseNotFound(ResourseNotFoundException ex)
    {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MaxLeaseLimitException.class)
    public ResponseEntity<?> handleMaxLeaseLimit(MaxLeaseLimitException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", "Maximum lease limit reached only 2 active lease allowed");
        return  new ResponseEntity<>(error ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
        Map<String , String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e-> error.put(e.getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex)
    {
        Map<String, String> error = new HashMap<>();
        error.put("error" , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalState(IllegalStateException ex){
        Map<String , String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
