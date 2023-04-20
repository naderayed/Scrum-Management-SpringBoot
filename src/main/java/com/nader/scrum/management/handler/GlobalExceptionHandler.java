package com.nader.scrum.management.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/*this class work like a listener for all kind of exception  happened in the controllers
 RestControllerAdvice annotation tell Spring that this class is the global Handler for all The API
    We Can implement more custom handler with different code error and so on,also we can implement a Controller Handler
that works only in the current controller, But DRY!!
*/

@RestControllerAdvice
public class GlobalExceptionHandler {

//Handler that Handel all Type of Exception but to be more precise we can create of every exception their own method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(HttpServletRequest request, Exception exception){

        var response = new ApiExceptionResponse(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);

    }
}
