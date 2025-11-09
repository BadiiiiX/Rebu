package fr.mmp.rebu.ride.dao;

import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

/**
 * Interface définissant les opérations de base pour la gestion des trajets (rides) en base de données.
 */
public interface RideDAO {

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
     * Ajoute un passager à un trajet.
     *
     * @param rideId      l'identifiant du trajet
     * @param passengerId l'identifiant du passager à ajouter
     */
    void addPassenger(int rideId, int passengerId);

    /**
     * Enregistre un nouveau trajet.
     *
     * @param ride le trajet à enregistrer
     * @return l'identifiant généré pour le trajet
     */
    int save(RideInterface ride);

    /**
     * Supprime un trajet selon son identifiant.
     *
     * @param rideId l'identifiant du trajet à supprimer
     */
    void deleteById(int rideId);

    /**
     * Retourne la liste des passagers d’un trajet donné.
     *
     * @param rideId l'identifiant du trajet
     * @return une liste de passagers
     */
    List<UserInterface> findPassengers(int rideId);

    /**
     * Recherche tous les trajets auxquels un passager participe.
     *
     * @param passengerId l'identifiant du passager
     * @return une liste de trajets associés à ce passager
     */
    List<RideInterface> findByPassengerId(int passengerId);

    /**
     * Retire un passager d’un trajet.
     *
     * @param rideId      l'identifiant du trajet
     * @param passengerId l'identifiant du passager à retirer
     */
    void removePassenger(int rideId, int passengerId);

    /**
     * Recherche tous les trajets créés par un conducteur.
     *
     * @param userId l'identifiant du conducteur
     * @return une liste de trajets appartenant à ce conducteur
     */
    List<RideInterface> findByDriverId(int userId);

    /**
     * Recherche un passager spécifique dans un trajet.
     *
     * @param rideId      l'identifiant du trajet
     * @param passengerId l'identifiant du passager
     * @return le passager correspondant, ou {@code null} s'il n'est pas trouvé
     */
    UserInterface findPassengerById(int rideId, int passengerId);
}
