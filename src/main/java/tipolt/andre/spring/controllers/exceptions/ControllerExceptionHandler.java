package tipolt.andre.spring.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tipolt.andre.spring.exceptions.InvalidJWTException;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<StandardError> invalidJWT(InvalidJWTException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Invalid JWT", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> categoryNotFoud(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Object Not Found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }
    
    @ExceptionHandler(PasswordNotCoincideException.class)
    public ResponseEntity<StandardError> passwordNotCoincide(PasswordNotCoincideException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Password Exception", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }
}
