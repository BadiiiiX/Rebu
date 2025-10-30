package main.java.com.carpooling.car.service;

import main.java.com.carpooling.car.exception.CarNotFoundException;
import main.java.com.carpooling.car.exception.DatabaseException;
import main.java.com.carpooling.car.exception.DuplicateCarException;
import main.java.com.carpooling.car.exception.InvalidCarDataException;
import main.java.com.carpooling.car.model.Car;

import java.util.List;

public interface CarService {
    
    /**
     * Enregistrer une nouvelle voiture
     * @param car la voiture à enregistrer
     * @return la voiture enregistrée
     * @throws InvalidCarDataException si les données sont invalides
     * @throws DuplicateCarException si la plaque existe déjà
     */
    Car register(Car car) throws InvalidCarDataException, DuplicateCarException;
    
    /**
     * Trouver une voiture par sa plaque d'immatriculation
     * @param plate la plaque d'immatriculation de la voiture
     * @return la voiture trouvée
     * @throws CarNotFoundException si la voiture n'existe pas
     */
    Car getByPlate(String plate) throws CarNotFoundException;

    
    /**
     * Récupérer toutes les voitures
     * @return liste de toutes les voitures
     */
    List<Car> getAll() ;

    
    /**
     * Récupérer les voitures d'un propriétaire
     * @param ownerId l'ID du propriétaire
     * @return liste des voitures du propriétaire
     */
    List<Car> getCarsByOwner(Long ownerId); //Todo Change this
    
    /**
     * Mettre à jour une voiture
     * @param car la voiture avec les nouvelles données
     * @return la voiture mise à jour
     * @throws InvalidCarDataException si les données sont invalides
     * @throws CarNotFoundException si la voiture n'existe pas
     */
    Car update(Car car) throws InvalidCarDataException, CarNotFoundException;
    
    /**
     * Supprimer définitivement une voiture
     * @param plate la plaque d'immatriculation de la voiture
     * @throws CarNotFoundException si la voiture n'existe pas
     */
    void delete(String plate) throws CarNotFoundException;

    /**
     * Valider les données d'une voiture
     * @param car la voiture à valider
     * @throws InvalidCarDataException si les données sont invalides
     */
    void validateCar(Car car) throws InvalidCarDataException; //todo enlever ça
}
