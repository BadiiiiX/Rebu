package fr.mmp.rebu.exception;

public class MappingException extends RebuException{
    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(String message) {
        super(message);
    }
}
