package fr.mmp.rebu.User;

public class UserFactory {

    public static UserInterface build(String username, String mail) {
        return new User(mail, username);
    }

}
