package tipolt.andre.spring.exceptions;

public class PasswordNotCoincideException extends RuntimeException {

    public PasswordNotCoincideException(String msg) {
        super(msg);
    }
}
