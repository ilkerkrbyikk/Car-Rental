package com.Ilker.handler;


import com.Ilker.exceptions.BrandAlreadyExistsException;
import com.Ilker.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    //*TODO: EXCEPTION CLASSINDAKI TUM EXCEPTIONLARI TEK TEK HANDLE ET.

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({BrandAlreadyExistsException.class})
    public ResponseEntity<Object> handleBrandAlreadyExistsException(BrandAlreadyExistsException e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    //*TODO: DEVAMINI YAZ....
}
