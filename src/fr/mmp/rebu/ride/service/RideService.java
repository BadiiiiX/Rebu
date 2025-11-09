package fr.mmp.rebu.ride.service;

import fr.mmp.rebu.ride.model.RideInterface;

import java.util.List;

/**
 * Interface définissant les services liés à la gestion des trajets (rides).
 */
public interface RideService {

    /**
     * Crée un nouveau trajet.
     *
     * @param ride le trajet à créer
     * @return le trajet créé
     */
    RideInterface createRide(RideInterface ride);

    /**
     * Ajoute un passager à un trajet existant.
     *
     * @param rideId      l'identifiant du trajet
     * @param passengerId l'identifiant du passager à ajouter
     */
    void addPassengerToRide(int rideId, int passengerId);

    /**
     * Retire un passager d’un trajet.
     *
     * @param rideId      l'identifiant du trajet
     * @param passengerId l'identifiant du passager à retirer
     */
    void removePassengerFromRide(int rideId, int passengerId);

    /**
     * Supprime un trajet à partir de son identifiant.
     *
     * @param rideId l'identifiant du trajet à supprimer
     */
    void deleteRide(int rideId);

    /**
     * Retourne la liste de tous les trajets.
     *
     * @return une liste de trajets
     */
    List<RideInterface> findAll();

    /**
     * Recherche un trajet par son identifiant.
     *
     * @param rideId l'identifiant du trajet
     * @return le trajet correspondant, ou {@code null} si non trouvé
     */
    RideInterface findById(int rideId);

    /**
     * Recherche tous les trajets auxquels un passager participe.
     *
     * @param passengerId l'identifiant du passager
     * @return une liste de trajets du passager
     */
    List<RideInterface> findByPassenger(int passengerId);

    /**
     * Recherche tous les trajets créés par un conducteur.
     *
     * @param userId l'identifiant du conducteur
     * @return une liste de trajets appartenant à ce conducteur
     */
    List<RideInterface> findByDriver(int userId);
}
