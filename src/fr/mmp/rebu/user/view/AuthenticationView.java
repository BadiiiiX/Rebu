package fr.mmp.rebu.user.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.cli.CliApp;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.shared.view.ChooseActionView;

public class AuthenticationView {

    public static void login() {

        var login = CliUtils.askString("[AUTH] Entrez votre nom d'utilisateur ou mail :");
        var pass = CliUtils.askString("[AUTH] Entrez votre mot de passe :");

        var user = Rebu.getUserService().verifyUserAuthentication(login, pass);

        if (user == null) {
            CliUtils.error("Authentication échouée. Vos informations de connexion sont incorrects");
            login();
            return;
        }


        CliUtils.success("Vous êtes bien connecté.");
        CliUtils.info("Actuellement connecté en tant que " + user.getUsername());
        CliApp.setUserLogged(user);
        CliApp.setIsDriver(!Rebu.getCarService().findCarsByOwner(user.getUserId()).isEmpty());

        ChooseActionView.choose();
    }

    public static void logout() {
        CliApp.setUserLogged(null);
        CliApp.setIsDriver(false);
        CliUtils.success("Vous êtes bien déconnecté.");
        login();
    }

}
