package com.nader.scrum.management.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*this class work like a listener for all kind of exception  happened in the controllers
 RestControllerAdvice annotation tell Spring that this class is the global Handler for all The API
    We Can implement more custom handler with different code error and so on,also we can implement a Controller Handler
that works only in the current controller, But DRY!!
*/

@RestControllerAdvice
public class GlobalExceptionHandler {

//Handler that Handel all Type of Exception but to be more precise we can create of every exception their own method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
