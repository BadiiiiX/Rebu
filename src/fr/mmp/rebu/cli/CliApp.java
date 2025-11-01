package fr.mmp.rebu.cli;

import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.user.view.AuthenticationView;

public class CliApp {

    private static UserInterface userLogged;
    private static boolean isDriver;

    public static void run() {

        CliUtils.printTitle("Rebu CLI App");
        System.out.println("Avant de commencer, connectez-vous.");

        AuthenticationView.login();

    }

    public static UserInterface getUserLogged() {
        return userLogged;
    }

    public static boolean getIsDriver() { return isDriver; }

    public static void setUserLogged(UserInterface userLogged) {
        CliApp.userLogged = userLogged;
    }

    public static void setIsDriver(boolean isDriver) { CliApp.isDriver = isDriver; }
}
