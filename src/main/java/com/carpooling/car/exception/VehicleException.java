package main.java.com.carpooling.car.exception;

/**
 * Exception de base pour toutes les exceptions liées aux véhicules
 */
public class VehicleException extends Exception {
    
    public VehicleException(String message) {
        super(message);
    }
    
    public VehicleException(String message, Throwable cause) {
        super(message, cause);
    }
}
