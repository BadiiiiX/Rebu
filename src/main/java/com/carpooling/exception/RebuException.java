package main.java.com.carpooling.exception;

public class RebuException extends Exception {
    public RebuException(String message) {
        super(message);
    }
    public RebuException(String message, Throwable cause) {
        super(message, cause);
    }
}
