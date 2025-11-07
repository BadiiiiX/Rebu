package fr.mmp.rebu.user.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.cli.CliApp;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.view.ChooseActionView;

public class AuthenticationView {

    public static void login() {

        var login = CliUtils.askString("[AUTH] Entrez votre nom d'utilisateur ou mail :");
        var pass = CliUtils.askString("[AUTH] Entrez votre mot de passe :");

        var user = Rebu.getUserService().verifyUserAuthentication(login, pass);

        if (user == null) {
            login();
            return;
        }

        CliApp.setUserLogged(user);
        CliApp.setIsDriver(!Rebu.getCarService().findCarsByOwner(user.getUserId()).isEmpty());

        ChooseActionView.choose();
    }

    public static void logout() {
        Rebu.getUserService().logoutUser(CliApp.getUserLogged());
        CliApp.setUserLogged(null);
        CliApp.setIsDriver(false);
        login();
    }

}
