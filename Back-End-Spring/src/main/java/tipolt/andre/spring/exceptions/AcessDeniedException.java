package tipolt.andre.spring.exceptions;

public class AcessDeniedException extends RuntimeException{
    
    public AcessDeniedException(String message){
        super(message);
    }
}
