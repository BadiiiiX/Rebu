package fr.mmp.rebu;

import fr.mmp.rebu.User.UserInterface;

import java.util.HashMap;

public class Rebu {

    public static Rebu _instance = null;

    public final HashMap<String, UserInterface> users = new HashMap<>();

    public static Rebu getInstance() {
        if (_instance == null) {
            _instance = new Rebu();
        }
        return _instance;
    }

    public UserInterface getUser(String mail) {
        return users.get(mail);
    }
}
