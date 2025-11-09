package fr.mmp.rebu.user.service;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

/**
 * Interface définissant les services liés à la gestion des utilisateurs.
 */
public interface UserService {

    /**
     * Crée un nouvel utilisateur.
     *
     * @param user l'utilisateur à créer
     * @return l'utilisateur créé
     */
    UserInterface createUser(UserInterface user);

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param user l'utilisateur à mettre à jour
     */
    void updateUser(UserInterface user);

    /**
     * Supprime un utilisateur à partir de son identifiant.
     *
     * @param userId l'identifiant de l'utilisateur à supprimer
     */
    void deleteUser(int userId);

    /**
     * Vérifie les informations de connexion d'un utilisateur.
     *
     * @param login    l'adresse e-mail ou le nom d'utilisateur
     * @param password le mot de passe
     * @return l'utilisateur correspondant si l'authentification réussit, sinon {@code null}
     */
    UserInterface verifyUserAuthentication(String login, String password);

    /**
     * Déconnecte un utilisateur.
     *
     * @param userId l'utilisateur à déconnecter
     */
    void logoutUser(UserInterface userId);

    /**
     * Recherche un utilisateur par son identifiant.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant, ou {@code null} s'il n'existe pas
     */
    UserInterface findUserById(int userId);

    /**
     * Recherche un utilisateur par son adresse e-mail.
     *
     * @param email l'adresse e-mail à rechercher
     * @return l'utilisateur correspondant, ou {@code null} si non trouvé
     */
    UserInterface findUserByEmail(String email);

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur à rechercher
     * @return l'utilisateur correspondant, ou {@code null} si non trouvé
     */
    UserInterface findUserByUsername(String username);

    /**
     * Retourne la liste de tous les utilisateurs.
     *
     * @return une liste d'utilisateurs
     */
    List<UserInterface> getAllUsers();

    /**
     * Retourne la liste des voitures appartenant à un utilisateur.
     *
     * @param userId l'identifiant du propriétaire
     * @return une liste de voitures appartenant à cet utilisateur
     */
    List<CarInterface> getCarsByOwner(int userId);
}
