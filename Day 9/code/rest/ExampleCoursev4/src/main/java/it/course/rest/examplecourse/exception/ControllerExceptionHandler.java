package it.course.rest.examplecourse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String,String>>resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    Map<String, String> message = new HashMap();
    message.put(ex.getClass().getName(), ex.getMessage());
    return new ResponseEntity<Map<String,String>>(message,HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String,String>> globalExceptionHandler(Exception ex, WebRequest request) {
    Map<String, String> message = new HashMap();
    message.put(ex.getClass().getName(), ex.getMessage());
    return new ResponseEntity<Map<String,String>>(message,HttpStatus.INTERNAL_SERVER_ERROR);
  }
}