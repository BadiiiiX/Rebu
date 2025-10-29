package main.java.com.carpooling.Vehicle.service;

import main.java.com.carpooling.Vehicle.exception.CarNotFoundException;
import main.java.com.carpooling.Vehicle.exception.DatabaseException;
import main.java.com.carpooling.Vehicle.exception.DuplicateCarException;
import main.java.com.carpooling.Vehicle.exception.InvalidCarDataException;
import main.java.com.carpooling.Vehicle.model.Car;

import java.util.List;

public interface CarService {
    
    /**
     * Enregistrer une nouvelle voiture
     * @param car la voiture à enregistrer
     * @return la voiture enregistrée
     * @throws InvalidCarDataException si les données sont invalides
     * @throws DuplicateCarException si la plaque existe déjà
     * @throws DatabaseException si une erreur de BDD survient
     */
    Car registerCar(Car car) throws InvalidCarDataException, DuplicateCarException, DatabaseException;
    
    /**
     * Trouver une voiture par son ID
     * @param id l'ID de la voiture
     * @return la voiture trouvée
     * @throws CarNotFoundException si la voiture n'existe pas
     * @throws DatabaseException si une erreur de BDD survient
     */
    Car getCarById(Long id) throws CarNotFoundException, DatabaseException;
    
    /**
     * Trouver une voiture par sa plaque d'immatriculation
     * @param licensePlate la plaque
     * @return la voiture trouvée
     * @throws CarNotFoundException si la voiture n'existe pas
     * @throws DatabaseException si une erreur de BDD survient
     */
    Car getCarByLicensePlate(String licensePlate) throws CarNotFoundException, DatabaseException;
    
    /**
     * Récupérer toutes les voitures
     * @return liste de toutes les voitures
     * @throws DatabaseException si une erreur de BDD survient
     */
    List<Car> getAllCars() throws DatabaseException;

    
    /**
     * Récupérer les voitures d'un propriétaire
     * @param ownerId l'ID du propriétaire
     * @return liste des voitures du propriétaire
     * @throws DatabaseException si une erreur de BDD survient
     */
    List<Car> getCarsByOwner(Long ownerId) throws DatabaseException;
    
    /**
     * Mettre à jour une voiture
     * @param car la voiture avec les nouvelles données
     * @return la voiture mise à jour
     * @throws InvalidCarDataException si les données sont invalides
     * @throws CarNotFoundException si la voiture n'existe pas
     * @throws DatabaseException si une erreur de BDD survient
     */
    Car updateCar(Car car) throws InvalidCarDataException, CarNotFoundException, DatabaseException;
    
    /**
     * Supprimer définitivement une voiture
     * @param id l'ID de la voiture
     * @throws CarNotFoundException si la voiture n'existe pas
     * @throws DatabaseException si une erreur de BDD survient
     */
    void deleteCar(Long id) throws CarNotFoundException, DatabaseException;

    /**
     * Valider les données d'une voiture
     * @param car la voiture à valider
     * @throws InvalidCarDataException si les données sont invalides
     */
    void validateCar(Car car) throws InvalidCarDataException;
    
    /**
     * Obtenir le nombre total de voitures
     * @return le nombre de voitures
     * @throws DatabaseException si une erreur de BDD survient
     */
    int getTotalCars() throws DatabaseException;
}
