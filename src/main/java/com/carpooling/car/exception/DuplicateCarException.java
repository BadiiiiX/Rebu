package main.java.com.carpooling.car.exception;

/**
 * Exception levée lorsqu'une voiture existe déjà (plaque d'immatriculation en doublon)
 */
public class DuplicateCarException extends VehicleException {
    
    public DuplicateCarException(String licensePlate) {
        super("Une voiture avec la plaque d'immatriculation " + licensePlate + " existe déjà");
    }
    
    public DuplicateCarException(String message, Throwable cause) {
        super(message, cause);
    }
}
