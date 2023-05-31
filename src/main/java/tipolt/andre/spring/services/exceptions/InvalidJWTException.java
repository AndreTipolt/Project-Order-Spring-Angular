package tipolt.andre.spring.services.exceptions;

public class InvalidJWTException extends RuntimeException{
    
    public InvalidJWTException(String msg){
        super(msg);
    }
}
