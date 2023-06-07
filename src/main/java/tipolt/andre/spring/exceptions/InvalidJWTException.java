package tipolt.andre.spring.exceptions;

public class InvalidJWTException extends RuntimeException{
    
    public InvalidJWTException(String msg){
        super(msg);
    }
}
