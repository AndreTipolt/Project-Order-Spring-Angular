package tipolt.andre.spring.exceptions;

public class BadCredentialsException extends RuntimeException{
    
    public BadCredentialsException(String message){
        super(message);
    }
}
