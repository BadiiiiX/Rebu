package main.java.com.carpooling.exception;

public class AlreadyExistsException extends RebuException{
    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
