package com.example.demo.exception;

import com.example.demo.dto.ErrorDTO;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        log.error("Error at class " + e.getClass() + ": " + e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<Object> handleUserUpdateException(UserUpdateException e) {
        log.error("Error at class " + e.getClass() + ": " + e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(UserDeleteException.class)
    public ResponseEntity<Object> handleUserDeleteException(UserDeleteException e) {
        log.error("Error at class " + e.getClass() + ": " + e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("Error at class " + e.getClass() + ": " + e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ResourceUpdateException.class)
    public ResponseEntity<Object> handleResourceUpdateException (ResourceUpdateException e) {
        log.error("Error at class " + e.getClass() + ": " + e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleGlobalException(RuntimeException e) {
        log.error("Error in class " + e.getClass() + ": " + e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }
}
