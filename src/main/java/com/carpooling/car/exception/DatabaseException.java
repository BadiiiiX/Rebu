package main.java.com.carpooling.car.exception;

/**
 * Exception levée lors d'erreurs de base de données
 */
public class DatabaseException extends VehicleException {
    
    public DatabaseException(String message) {
        super(message);
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
