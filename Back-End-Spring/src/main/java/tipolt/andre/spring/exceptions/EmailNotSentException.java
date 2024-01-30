package tipolt.andre.spring.exceptions;

public class EmailNotSentException extends RuntimeException{
    
    public EmailNotSentException(String message){
        super(message);
    }
}
