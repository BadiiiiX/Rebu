package fr.mmp.rebu.user.service;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.domain.AbstractService;
import fr.mmp.rebu.user.dao.UserDAO;
import fr.mmp.rebu.user.factory.UserFactory;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

import static fr.mmp.rebu.user.factory.UserFactory.TEMPORARY_ID;

public class UserServiceImpl extends AbstractService implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserInterface createUser(UserInterface user) {
        if(user.getUserId() != TEMPORARY_ID) {
            System.out.println("Impossible d'ajouter un utilisateur déjà existant (ID défini)");
            return null;
        }

        var userId = this.userDAO.save(user);

        return UserFactory.build(userId, user.getEmail(), user.getUsername(), user.getPassword());
    }

    @Override
    public void updateUser(UserInterface user) {
        if(user.getUserId() == TEMPORARY_ID) {
            System.out.println("L'utilisateur n'a pas d'identifiant");
            return;
        }

        this.userDAO.update(user);
    }

    @Override
    public void deleteUser(int userId) {
        if(userId == TEMPORARY_ID) {
            System.out.println("L'utilisateur n'a pas d'identifiant");
            return;
        }

        this.userDAO.delete(userId);
    }

    @Override
    public UserInterface verifyUserAuthentication(String login, String password) {

        var user = this.userDAO.findByMail(login);

        if(user == null) {
            user = this.userDAO.findByUsername(login);
            if(user == null) {
                return null;
            }
        }

        return user.getPassword().equals(password) ? user : null;
    }

    @Override
    public UserInterface findUserById(int userId) {
        if(userId == TEMPORARY_ID) {
            System.out.println("L'utilisateur n'a pas d'identifiant");
            return null;
        }

        return this.userDAO.findById(userId);
    }

    @Override
    public UserInterface findUserByEmail(String email) {
        return this.userDAO.findByMail(email);
    }

    @Override
    public UserInterface findUserByUsername(String username) {
        return this.userDAO.findByUsername(username);
    }

    @Override
    public List<UserInterface> getAllUsers() {
        return this.userDAO.findAll();
    }

    @Override
    public List<CarInterface> getCarsByOwner(int userId) {
        return Rebu.getCarService().findCarsByOwner(userId);
    }
}
