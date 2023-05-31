package tipolt.andre.spring.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tipolt.andre.spring.services.exceptions.InvalidJWTException;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<StandardError> invalidJWT(InvalidJWTException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Invalid JWT", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }
}
