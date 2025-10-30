package main.java.com.carpooling.car.exception;

/**
 * Exception levée lorsqu'une voiture n'est pas trouvée
 */
public class CarNotFoundException extends VehicleException {
    
    public CarNotFoundException(String message) {
        super(message);
    }
    
    public CarNotFoundException(Long carId) {
        super("Voiture avec l'ID " + carId + " non trouvée");
    }
    
    public CarNotFoundException(String licensePlate, boolean byPlate) {
        super("Voiture avec la plaque d'immatriculation " + licensePlate + " non trouvée");
    }
}
