package fr.mmp.rebu.user.service;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

public interface UserService {

    UserInterface createUser(UserInterface user);

    void updateUser(UserInterface user);

    void deleteUser(int userId);

    UserInterface verifyUserAuthentication(String login, String password);

    void logoutUser(UserInterface userId);

    UserInterface findUserById(int userId);

    UserInterface findUserByEmail(String email);

    UserInterface findUserByUsername(String username);

    List<UserInterface> getAllUsers();

    List<CarInterface> getCarsByOwner(int userId);
}
