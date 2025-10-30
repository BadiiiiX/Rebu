package main.java.com.carpooling.car.exception;

/**
 * Exception levée lorsque les données du véhicule sont invalides
 */
public class InvalidCarDataException extends VehicleException {
    
    public InvalidCarDataException(String message) {
        super(message);
    }
    
    public InvalidCarDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
