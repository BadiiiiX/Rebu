package fr.mmp.rebu.user.dao;

import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

public interface UserDAO {
    /**
     * Persists a given user into the database.
     *
     * @param user the user to be saved, represented by an implementation of the UserInterface.
     *             The user object should include relevant details such as email, username, and password.
     * @return an integer representing the ID of the saved user, or -1 if the operation fails.
     */
    int save(UserInterface user);

    /**
     * Updates the information of an existing user in the system.
     *
     * @param user an implementation of the UserInterface representing the user to be updated.
     *             The user object should include the necessary updated details such as email,
     *             username, and password, as well as a valid user ID.
     */
    void update(UserInterface user);

    /**
     * Deletes a user from the database based on the provided user ID.
     *
     * @param userId the unique ID of the user to be deleted from the system
     */
    void delete(int userId);

    /**
     * Retrieves a list of all users stored in the system.
     *
     * @return a list of objects implementing the UserInterface, representing all users in the system.
     *         If no users are found, returns an empty list.
     */
    List<UserInterface> findAll();

    /**
     * Retrieves a user from the database based on the provided user ID.
     *
     * @param userId the unique ID of the user to be retrieved
     * @return an implementation of the UserInterface representing the user
     *         if found, or null if no user with the given ID exists
     */
    UserInterface findById(int userId);

    /**
     * Retrieves a user from the database based on their email address.
     *
     * @param mail the email address of the user to be retrieved
     * @return an implementation of the UserInterface representing the user
     *         if found, or null if no user with the given email exists
     */
    UserInterface findByMail(String mail);

    /**
     * Retrieves a user from the database based on their username.
     *
     * @param username the username of the user to be retrieved
     * @return an implementation of the UserInterface representing the user
     *         if found, or null if no user with the given username exists
     */
    UserInterface findByUsername(String username);
}
