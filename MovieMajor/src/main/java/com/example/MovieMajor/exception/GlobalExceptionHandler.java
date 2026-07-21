package com.example.MovieMajor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.spi.ToolProvider.findFirst;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
            String message = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(err->err.getField()+ ";" + err.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation Failed");
return buildResponse(HttpStatus.BAD_REQUEST, message);
}

@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Something went Wrong: "+ ex.getMessage());
}

public ResponseEntity<Map<String,Object>> buildResponse(HttpStatus status, String message){
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",status.value());
        body.put("error",status.getReasonPhrase());
        body.put("message",message);
        return new ResponseEntity<>(body,status);
    }
}
