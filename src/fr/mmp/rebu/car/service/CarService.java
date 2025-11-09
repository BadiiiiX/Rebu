package fr.mmp.rebu.car.service;

import fr.mmp.rebu.car.model.CarInterface;

import java.util.List;

/**
 * Interface définissant les services liés à la gestion des voitures.
 */
public interface CarService {

    /**
     * Crée une nouvelle voiture.
     *
     * @param car la voiture à créer
     * @return la voiture créée
     */
    CarInterface createCar(CarInterface car);

    /**
     * Met à jour les informations d'une voiture existante.
     *
     * @param car la voiture à mettre à jour
     */
    void updateCar(CarInterface car);

    /**
     * Supprime une voiture à partir de sa plaque d'immatriculation.
     *
     * @param plate la plaque d'immatriculation
     */
    void deleteCar(String plate);

    /**
     * Recherche une voiture à partir de sa plaque d'immatriculation.
     *
     * @param plate la plaque d'immatriculation
     * @return la voiture correspondante, ou {@code null} si non trouvée
     */
    CarInterface findCarByPlate(String plate);

    /**
     * Retourne la liste de toutes les voitures.
     *
     * @return une liste de toutes les voitures
     */
    List<CarInterface> findAllCars();

    /**
     * Recherche toutes les voitures appartenant à un propriétaire donné.
     *
     * @param ownerId l'identifiant du propriétaire
     * @return une liste de voitures appartenant à ce propriétaire
     */
    List<CarInterface> findCarsByOwner(int ownerId);
}