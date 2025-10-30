package main.java.com.carpooling.car.dao;

import main.java.com.carpooling.car.model.Car;
import main.java.com.carpooling.car.exception.DatabaseException;
import main.java.com.carpooling.car.exception.CarNotFoundException;
import main.java.com.carpooling.car.exception.DuplicateCarException;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    
    /**
     * Créer une nouvelle voiture dans la base de données
     * @param car la voiture à créer
     * @return la voiture créée avec son ID généré
     * @throws DuplicateCarException si la plaque d'immatriculation existe déjà
     * @throws DatabaseException si une erreur de base de données survient
     */
    Car create(Car car) throws DuplicateCarException, DatabaseException;
    
    /**
     * Trouver une voiture par son ID
     * @param id l'identifiant de la voiture
     * @return Optional contenant la voiture si trouvée, vide sinon
     * @throws DatabaseException si une erreur de base de données survient
     */
    Optional<Car> findById(Long id) throws DatabaseException;
    
    /**
     * Trouver une voiture par sa plaque d'immatriculation
     * @param licensePlate la plaque d'immatriculation
     * @return Optional contenant la voiture si trouvée, vide sinon
     * @throws DatabaseException si une erreur de base de données survient
     */
    Optional<Car> findByLicensePlate(String licensePlate) throws DatabaseException;
    
    /**
     * Récupérer toutes les voitures
     * @return Liste de toutes les voitures
     * @throws DatabaseException si une erreur de base de données survient
     */
    List<Car> findAll() throws DatabaseException;
    
    /**
     * Mettre à jour une voiture existante
     * @param car la voiture avec les nouvelles données
     * @return la voiture mise à jour
     * @throws CarNotFoundException si la voiture n'existe pas
     * @throws DatabaseException si une erreur de base de données survient
     */
    Car update(Car car) throws CarNotFoundException, DatabaseException;
    
    /**
     * Supprimer définitivement une voiture de la base de données
     * @param id l'ID de la voiture à supprimer
     * @return true si la suppression a réussi
     * @throws CarNotFoundException si la voiture n'existe pas
     * @throws DatabaseException si une erreur de base de données survient
     */
    boolean hardDelete(Long id) throws CarNotFoundException, DatabaseException;
    
    /**
     * Vérifier si une plaque d'immatriculation existe déjà
     * @param licensePlate la plaque à vérifier
     * @return true si la plaque existe, false sinon
     * @throws DatabaseException si une erreur de base de données survient
     */
    boolean existsByLicensePlate(String licensePlate) throws DatabaseException;
    
    /**
     * Compter le nombre total de voitures
     * @return le nombre de voitures
     * @throws DatabaseException si une erreur de base de données survient
     */
    int count() throws DatabaseException;
}
