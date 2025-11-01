package fr.mmp.rebu.shared.view;

import fr.mmp.rebu.car.view.CarView;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.ride.view.RideDriverView;
import fr.mmp.rebu.ride.view.RideView;
import fr.mmp.rebu.user.view.AuthenticationView;

public class ChooseActionView {

    public static void choose() {

        var choice = CliUtils.askChoice("Que souhaîtez-vous faire ?",
                "Gérer mes voitures",
                "Gérer mes trajets",
                "Voir les trajets disponibles",
                "Se déconnecter");


        switch (choice) {
            case 1:
                CarView.choose();
                break;
            case 2:
                RideDriverView.choose();
                break;
            case 3:
                RideView.choose();
                break;
            case 4:
                AuthenticationView.logout();

        }



    }

}
