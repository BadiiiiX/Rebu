package fr.mmp.rebu.ride.model;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.Date;
import java.util.List;

/**
 * Interface représentant un trajet (ride).
 */
public interface RideInterface {

    /**
     * Retourne l'identifiant du trajet.
     *
     * @return l'identifiant du trajet
     */
    int getRideId();

    /**
     * Retourne la ville ou le point de départ du trajet.
     *
     * @return l'origine du trajet
     */
    String getOrigin();

    /**
     * Retourne la destination du trajet.
     *
     * @return la destination du trajet
     */
    String getDestination();

    /**
     * Retourne la date de départ du trajet.
     *
     * @return la date de départ
     */
    Date getStartDate();

    /**
     * Retourne le conducteur du trajet.
     *
     * @return le conducteur
     */
    UserInterface getDriver();

    /**
     * Retourne la liste des passagers du trajet.
     *
     * @return la liste des passagers
     */
    List<UserInterface> getPassengers();

    /**
     * Ajoute un passager au trajet.
     *
     * @param passenger le passager à ajouter
     * @return {@code true} si le passager a été ajouté, sinon {@code false}
     */
    boolean addPassenger(UserInterface passenger);

    /**
     * Retire un passager du trajet.
     *
     * @param passenger le passager à retirer
     * @return {@code true} si le passager a été retiré, sinon {@code false}
     */
    boolean removePassenger(UserInterface passenger);

    /**
     * Retourne la voiture utilisée pour le trajet.
     *
     * @return la voiture du trajet
     */
    CarInterface getCar();

    /**
     * Retourne une représentation textuelle du trajet.
     *
     * @return une chaîne décrivant le trajet
     */
    String toString();
}

