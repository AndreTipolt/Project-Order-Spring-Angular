package tipolt.andre.spring.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import tipolt.andre.spring.exceptions.AcessDeniedException;
import tipolt.andre.spring.exceptions.BadCredentialsException;
import tipolt.andre.spring.exceptions.EmailNotSentException;
import tipolt.andre.spring.exceptions.ErrorSaveDataInRedisException;
import tipolt.andre.spring.exceptions.InvalidJWTException;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;
import tipolt.andre.spring.services.exceptions.ForbiddenException;
import tipolt.andre.spring.services.exceptions.UnauthorizedException;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError err = new ValidationError();
        err.setTimeStamp(System.currentTimeMillis());
        err.setStatus(httpStatus.value());
        err.setMessage("Validation Exception");
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            err.addError(f.getField(), f.getDefaultMessage());
        }
        
        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        OAuthCustomError err = new OAuthCustomError("Forbidden", e.getMessage());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<OAuthCustomError> unathorized(UnauthorizedException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        OAuthCustomError err = new OAuthCustomError("Unathorized", e.getMessage());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(BadCredentialsException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Bad Credentials", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(AcessDeniedException.class)
    public ResponseEntity<StandardError> acessDenied(AcessDeniedException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Unauthorized", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardError> endPointNotFound(NoResourceFoundException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "EndPoint Not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(ErrorSaveDataInRedisException.class)
    public ResponseEntity<StandardError> errorSaveDataInRedis(ErrorSaveDataInRedisException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Error Redis", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(EmailNotSentException.class)
    public ResponseEntity<StandardError> emailNotSent(EmailNotSentException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Email Error", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

}
