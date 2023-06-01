package tipolt.andre.spring.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    
    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
