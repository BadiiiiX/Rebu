package fr.mmp.rebu.exception;

public class NotFoundException extends RebuException{
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
