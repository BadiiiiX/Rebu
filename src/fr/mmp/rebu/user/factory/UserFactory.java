package fr.mmp.rebu.user.factory;

import fr.mmp.rebu.user.model.User;
import fr.mmp.rebu.user.model.UserInterface;

public class UserFactory {

    public static final int TEMPORARY_ID = -1;

    public static UserInterface build(String email, String username, String password) {
        return new User(TEMPORARY_ID, email, username, password);
    }

    public static UserInterface build(int id, String email, String username, String password) {
        return new User(id, email, username, password);
    }

}
