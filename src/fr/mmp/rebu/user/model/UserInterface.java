package fr.mmp.rebu.user.model;

import fr.mmp.rebu.car.model.CarInterface;

import java.util.Map;

/**
 * Interface représentant un utilisateur du système.
 */
public interface UserInterface {

    /**
     * Retourne l'identifiant unique de l'utilisateur.
     *
     * @return l'identifiant de l'utilisateur
     */
    Integer getUserId();

    /**
     * Retourne l'adresse e-mail de l'utilisateur.
     *
     * @return l'adresse e-mail
     */
    String getEmail();

    /**
     * Retourne le nom d'utilisateur.
     *
     * @return le nom d'utilisateur
     */
    String getUsername();

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return le mot de passe
     */
    String getPassword();

    /**
     * Retourne la liste des voitures appartenant à l'utilisateur.
     *
     * @return une map contenant les voitures, avec la plaque d'immatriculation comme clé
     */
    Map<String, CarInterface> getCars();

    /**
     * Ajoute une voiture à l'utilisateur.
     *
     * @param car la voiture à ajouter
     * @return {@code true} si la voiture a été ajoutée, sinon {@code false}
     */
    boolean addCar(CarInterface car);

    /**
     * Supprime une voiture appartenant à l'utilisateur selon sa plaque d'immatriculation.
     *
     * @param plate la plaque d'immatriculation de la voiture à retirer
     */
    void removeCar(String plate);
}
