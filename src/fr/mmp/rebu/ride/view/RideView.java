package fr.mmp.rebu.ride.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.view.ChooseActionView;

public class RideView {
    public static void choose() {
        CliUtils.clearScreen();

        var choice = CliUtils.askChoice("[RIDE] Que souhaîtez-vous faire ?",
                "Afficher les trajets disponibles",
                "Retour au menu principal");

        switch (choice) {
            case 1:
                show();
                break;
            case 2:
                ChooseActionView.choose();
        }
    }

    private static void show() {
        CliUtils.clearScreen();

        Rebu.getRideService().findAll().forEach((ride) -> {
            CliUtils.print(ride.toString());
            System.out.println("--------------------------------------------------");
        });

        choose();
    }
}
