package fr.mmp.rebu.user.dao;

import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

/**
 * Interface définissant les opérations de base pour la gestion des utilisateurs en base de données.
 */
public interface UserDAO {

    /**
     * Enregistre un nouvel utilisateur dans la base de données.
     *
     * @param user l'utilisateur à enregistrer, implémentant {@link UserInterface}.
     *             L'objet doit contenir les informations nécessaires comme l'email, le nom d'utilisateur et le mot de passe.
     * @return l'identifiant de l'utilisateur créé, ou -1 si l'opération échoue.
     */
    int save(UserInterface user);

    /**
     * Met à jour les informations d’un utilisateur existant dans la base.
     *
     * @param user l'utilisateur à mettre à jour, implémentant {@link UserInterface}.
     *             L'objet doit contenir les informations mises à jour (email, nom d'utilisateur, mot de passe)
     *             ainsi qu’un identifiant valide.
     */
    void update(UserInterface user);

    /**
     * Supprime un utilisateur de la base de données.
     *
     * @param userId l'identifiant unique de l'utilisateur à supprimer
     */
    void delete(int userId);

    /**
     * Retourne la liste de tous les utilisateurs enregistrés dans la base.
     *
     * @return une liste d'objets {@link UserInterface} représentant tous les utilisateurs.
     *         Retourne une liste vide s’il n’y a aucun utilisateur.
     */
    List<UserInterface> findAll();

    /**
     * Recherche un utilisateur selon son identifiant.
     *
     * @param userId l'identifiant unique de l'utilisateur à rechercher
     * @return l'utilisateur correspondant, ou {@code null} s'il n'existe pas
     */
    UserInterface findById(int userId);

    /**
     * Recherche un utilisateur selon son adresse e-mail.
     *
     * @param mail l'adresse e-mail de l'utilisateur à rechercher
     * @return l'utilisateur correspondant, ou {@code null} si aucun utilisateur n'est trouvé
     */
    UserInterface findByMail(String mail);

    /**
     * Recherche un utilisateur selon son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur à rechercher
     * @return l'utilisateur correspondant, ou {@code null} si aucun utilisateur n'est trouvé
     */
    UserInterface findByUsername(String username);
}
