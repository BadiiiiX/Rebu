package fr.mmp.rebu.car.model;

import fr.mmp.rebu.user.model.UserInterface;

/**
 * Interface représentant une voiture.
 */
public interface CarInterface {

    /**
     * Retourne la plaque d'immatriculation de la voiture.
     *
     * @return la plaque d'immatriculation
     */
    String getPlate();

    /**
     * Retourne le nombre de places disponibles dans la voiture.
     *
     * @return le nombre de passagers possibles
     */
    int getPassengersNumber();

    /**
     * Retourne le propriétaire de la voiture.
     *
     * @return le propriétaire de type {@link UserInterface}
     */
    UserInterface getOwner();
}