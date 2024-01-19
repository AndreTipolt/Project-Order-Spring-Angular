package tipolt.andre.spring.exceptions;

public class ErrorSaveDataInRedisException extends RuntimeException{
    
    public ErrorSaveDataInRedisException(String message){
        super(message);
    }
}
