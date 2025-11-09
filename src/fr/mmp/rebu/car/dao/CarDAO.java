package fr.mmp.rebu.car.dao;

import fr.mmp.rebu.car.model.CarInterface;

import java.util.List;

/**
 * Interface définissant les opérations de base pour la gestion des voitures en base de données.
 */
public interface CarDAO {

    /**
     * Enregistre une nouvelle voiture.
     *
     * @param car la voiture à enregistrer
     */
    void save(CarInterface car);

    /**
     * Met à jour une voiture existante.
     *
     * @param car la voiture à mettre à jour
     * @return la voiture mise à jour
     */
    CarInterface update(CarInterface car);

    /**
     * Recherche une voiture à partir de sa plaque d'immatriculation.
     *
     * @param plate la plaque d'immatriculation
     * @return la voiture correspondante, ou {@code null} si non trouvée
     */
    CarInterface findByPlate(String plate);

    /**
     * Retourne la liste de toutes les voitures.
     *
     * @return une liste de voitures
     */
    List<CarInterface> findAll();

    /**
     * Recherche toutes les voitures appartenant à un propriétaire donné.
     *
     * @param ownerId l'identifiant du propriétaire
     * @return une liste de voitures appartenant à ce propriétaire
     */
    List<CarInterface> findByOwner(int ownerId);

    /**
     * Supprime une voiture selon sa plaque d'immatriculation.
     *
     * @param plate la plaque d'immatriculation
     * @return {@code true} si la suppression a réussi, sinon {@code false}
     */
    boolean delete(String plate);
}